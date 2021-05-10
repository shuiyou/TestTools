package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundBaseBean;
import com.fund.bean.FundInvestPlanAddBean;
import com.fund.bean.FundMemberAddBean;
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
public class FundInvestPlanAdd extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundInvestPlanAdd")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean, @ModelAttribute("fipaBean") FundInvestPlanAddBean fipaBean, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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

        if (!fipaBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + fipaBean.getMember_id());
        }
        if (!fipaBean.getPayment_method_id().isEmpty()) {
            inputString.append(",payment_method_id=" + fipaBean.getPayment_method_id());
        }
            if (!fipaBean.getPayment_type().isEmpty()) {
            inputString.append(",payment_type=" + fipaBean.getPayment_type());
        }
        if (!fipaBean.getOut_request_no().isEmpty()) {
            inputString.append(",out_request_no=" + fipaBean.getOut_request_no());
        }
        if (!fipaBean.getFund_code().isEmpty()) {
            inputString.append(",fund_code=" + fipaBean.getFund_code());
        }
        if (!fipaBean.getTrade_amount().isEmpty()) {
            inputString.append(",trade_amount=" + fipaBean.getTrade_amount());
        }
        if (!fipaBean.getCycle_unit().isEmpty()) {
            inputString.append(",cycle_unit=" + fipaBean.getCycle_unit());
        }
        if (!fipaBean.getSend_day().isEmpty()) {
            inputString.append(",send_day=" + fipaBean.getSend_day());
        }
        if (!fipaBean.getTrade_cycle().isEmpty()) {
            inputString.append(",trade_cycle=" + fipaBean.getTrade_cycle());
        }
        if (!fipaBean.getRetry_times().isEmpty()) {
            inputString.append(",retry_times=" + fipaBean.getRetry_times());
        }
        if (!fipaBean.getTerminate_times().isEmpty()) {
            inputString.append(",terminate_times=" + fipaBean.getTerminate_times());
        }
        if (!fipaBean.getBegin_date().isEmpty()) {
            inputString.append(",begin_date=" + fipaBean.getBegin_date());
        }
        if (!fipaBean.getEnd_date().isEmpty()) {
            inputString.append(",end_date=" + fipaBean.getEnd_date());
        }
        if (!fipaBean.getOverstep_risk_level().isEmpty()) {
            inputString.append(",overstep_risk_level=" + fipaBean.getOverstep_risk_level());
        }
        if (!fipaBean.getRisk_notice().isEmpty()) {
            inputString.append(",risk_notice=" + fipaBean.getRisk_notice());
        }
        if (!fipaBean.getClient_ip().isEmpty()) {
            inputString.append(",client_ip=" + fipaBean.getClient_ip());
        }
        if (!fipaBean.getMac_address().isEmpty()) {
            inputString.append(",mac_address=" + fipaBean.getMac_address());
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

        fipaBean.setEncodeString(sortString);
        if (!fipaBean.getRisk_notice().isEmpty()) {
            fipaBean.setEncodeString(tool.removeFromString(fipaBean.getEncodeString(), "&", "&", "startswith", "risk_notice") + "&risk_notice=" + tool.textEncode(fipaBean.getRisk_notice(), "UTF-8"));
        }

        String requestString = fipaBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
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

