package com.controller.fund;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundBaseBean;
import com.fund.bean.FundOrderListGetBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yimeiweile on 2017/7/1.
 */
@Controller
public class FundOrderListGet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundOrderListGet")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean,
                       @ModelAttribute("folgBean") FundOrderListGetBean folgBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

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
        if (!fundBaseBean.getStart_time().isEmpty()) {
            inputString.append(",start_time=" + fundBaseBean.getStart_time());
        }
        if (!fundBaseBean.getEnd_time().isEmpty()) {
            inputString.append(",end_time=" + fundBaseBean.getEnd_time());
        }
        if (!fundBaseBean.getPage_no().isEmpty()) {
            inputString.append(",page_no=" + fundBaseBean.getPage_no());
        }
        if (!fundBaseBean.getPage_size().isEmpty()) {
            inputString.append(",page_size=" + fundBaseBean.getPage_size());
        }
        if (!folgBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + folgBean.getMember_id());
        }
        if (!folgBean.getTrade_order_no().isEmpty()){
            inputString.append(",trade_order_no=" + folgBean.getTrade_order_no());
        }
        if (!folgBean.getFund_code_list().isEmpty()) {
            inputString.append(",fund_code_list=" + folgBean.getFund_code_list());
        }
        if (!folgBean.getFilter_fund_code_list().isEmpty()) {
            inputString.append(",filter_fund_code_list=" + folgBean.getFilter_fund_code_list());
        }
        if (!folgBean.getPay_status_list().isEmpty()) {
            inputString.append(",pay_status_list=" + folgBean.getPay_status_list());
        }
        if (!folgBean.getConfirm_status_list().isEmpty()) {
            inputString.append(",confirm_status_list=" + folgBean.getConfirm_status_list());
        }
        if (!folgBean.getBusin_code_list().isEmpty()){
            inputString.append(",busin_code_list=" + folgBean.getBusin_code_list());
        }
        if (!folgBean.getPortfolio_apply_no().isEmpty()) {
            inputString.append(",portfolio_apply_no=" + folgBean.getPortfolio_apply_no
                    ());
        }

        System.out.println(inputString.toString());

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&", "startswith", "sign").replace("#", ",");

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

        folgBean.setEncodeString(sortString);



        String requestString = folgBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
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
