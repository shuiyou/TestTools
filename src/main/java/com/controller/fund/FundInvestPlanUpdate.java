package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundBaseBean;
import com.fund.bean.FundInvestPlanAddBean;
import com.fund.bean.FundInvestPlanUpdateBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunjie on 2017/6/2.
 */
@Controller
public class FundInvestPlanUpdate extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundInvestPlanUpdate")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean, @ModelAttribute("fipuBean") FundInvestPlanUpdateBean fipuBean, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RSAUtil rsa = new RSAUtil();
        MD5Util md5 = new MD5Util();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        StringBuilder inputString = new StringBuilder("");
        if (!fundBaseBean.getService().isEmpty()) {
            inputString.append("service=" + fundBaseBean.getService());
        }
        if (!fundBaseBean.getVersion().isEmpty()) {
            inputString.append(",version=" + fundBaseBean.getVersion());
        }
        if (!fundBaseBean.getRequest_time().isEmpty()) {
            inputString.append(",request_time=" + fundBaseBean.getRequest_time());
        }
        if (!fundBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + fundBaseBean.getPartner_id());
        }
        if (!fundBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + fundBaseBean.getSign_type());
        }
        if (!fundBaseBean.getSign_version().isEmpty()) {
            inputString.append(",sign_version=" + fundBaseBean.getSign_version());
        }
        if (!fundBaseBean.getEncrypt_version().isEmpty()) {
            inputString.append(",encrypt_version=" + fundBaseBean.getEncrypt_version());
        }
        if (!fundBaseBean.getPlatform_type().isEmpty()) {
            inputString.append(",platform_type=" + fundBaseBean.getPlatform_type());
        }
        if (!fundBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + fundBaseBean.getMemo());
        }


        if (!fipuBean.getTrade_amount().isEmpty()) {
            inputString.append(",trade_amount=" + fipuBean.getTrade_amount());
        }
        if (!fipuBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + fipuBean.getMember_id());
        }
        if (!fipuBean.getCycle_unit().isEmpty()) {
            inputString.append(",cycle_unit=" + fipuBean.getCycle_unit());
        }
        if (!fipuBean.getSend_day().isEmpty()) {
            inputString.append(",send_day=" + fipuBean.getSend_day());
        }
        if (!fipuBean.getTrade_cycle().isEmpty()) {
            inputString.append(",trade_cycle=" + fipuBean.getTrade_cycle());
        }
        if (!fipuBean.getRetry_times().isEmpty()) {
            inputString.append(",retry_times=" + fipuBean.getRetry_times());
        }
        if (!fipuBean.getTerminate_times().isEmpty()) {
            inputString.append(",terminate_times=" + fipuBean.getTerminate_times());
        }
        if (!fipuBean.getBegin_date().isEmpty()) {
            inputString.append(",begin_date=" + fipuBean.getBegin_date());
        }
        if (!fipuBean.getEnd_date().isEmpty()) {
            inputString.append(",end_date=" + fipuBean.getEnd_date());
        }
        if (!fipuBean.getProtocol_no().isEmpty()) {
            inputString.append(",protocol_no=" + fipuBean.getProtocol_no());
        }



        System.out.println(inputString.toString());

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&", "startswith", "sign").replace("#", ",");
        System.out.println(signString);
        String sign = null;
        if (fundBaseBean.getSign_type().equals("RSA")) {
            rsa.setPrivateKey(fundBaseBean.getRsakey());
            try {
                sign = rsa.sign(signString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (fundBaseBean.getSign_type().equals("MD5")) {
            String md5SignString = signString + fundBaseBean.getMd5key();
            sign = md5.getMD5(md5SignString, "UTF-8");
        }
        fipuBean.setEncodeString(sortString);

        String requestString = fipuBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.post(fundBaseBean.getUrl(), requestString);
        String dataString = tool.base64Decode(responseString.substring(responseString.indexOf("data=") + 5, responseString.indexOf("&sign")));
        System.out.println(responseString);

        request.setAttribute("response", dataString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

}

