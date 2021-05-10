package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundTradeBuyOrderAddBean;
import com.fund.bean.FundBaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yimeiweile on 2017/6/30.
 */
@Controller
public class FundTradeBuyOrderAdd extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundTradeBuyOrderAdd")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean,
                       @ModelAttribute("ftboaBean") FundTradeBuyOrderAddBean ftboaBean,
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
            inputString.append(",encrypt_version="
                    + fundBaseBean.getEncrypt_version());
        }
        if (!fundBaseBean.getPlatform_type().isEmpty()) {
            inputString.append(",platform_type="
                    + fundBaseBean.getPlatform_type());
        }
        if (!fundBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + fundBaseBean.getMemo());
        }
        if (!ftboaBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + ftboaBean.getMember_id());
        }
        if (!ftboaBean.getPayment_method_id().isEmpty()) {
            inputString.append(",payment_method_id=" + ftboaBean.getPayment_method_id());
        }
        if (!ftboaBean.getPayment_type().isEmpty()) {
            inputString.append(",payment_type=" + ftboaBean.getPayment_type());
        }
        if (!ftboaBean.getOut_request_no().isEmpty()) {
            inputString.append(",out_request_no=" + ftboaBean.getOut_request_no());
        }
        if (!ftboaBean.getBusin_code().isEmpty()) {
            inputString.append(",busin_code=" + ftboaBean.getBusin_code());
        }
        if (!ftboaBean.getFund_code().isEmpty()) {
            inputString.append(",fund_code=" + ftboaBean.getFund_code());
        }
        if (!ftboaBean.getTrade_amount().isEmpty()) {
            inputString.append(",trade_amount=" + ftboaBean.getTrade_amount());
        }

        if (!ftboaBean.getOverstep_risk_level().isEmpty()) {
            inputString.append(",overstep_risk_level=" + ftboaBean.getOverstep_risk_level());
        }
        if (!ftboaBean.getRisk_notice().isEmpty()) {
            inputString.append(",risk_notice=" + ftboaBean.getRisk_notice());
        }
        if (!ftboaBean.getClient_ip().isEmpty()) {
            inputString.append(",client_ip=" + ftboaBean.getClient_ip());
        }
        if (!ftboaBean.getMac_address().isEmpty()) {
            inputString.append(",mac_address=" + ftboaBean.getMac_address());
        }

        System.out.println(inputString.toString());

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&",
                "startswith", "sign").replace("#", ",");

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

        ftboaBean.setEncodeString(sortString);
        if (!ftboaBean.getRisk_notice().isEmpty()) {
            ftboaBean.setEncodeString(tool.removeFromString(ftboaBean.getEncodeString(), "&", "&", "startswith", "risk_notice") + "&risk_notice=" + tool.textEncode(ftboaBean.getRisk_notice(), "UTF-8"));
        }
        String requestString = ftboaBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.post(fundBaseBean.getUrl(), requestString);
        String dataString = tool.base64Decode(responseString.substring(responseString.indexOf("data=") + 5, responseString.indexOf("&sign")));
        System.out.println(responseString);

        request.setAttribute("response", dataString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }

}