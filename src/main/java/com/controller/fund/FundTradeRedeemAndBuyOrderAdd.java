package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundBaseBean;
import com.fund.bean.FundTradeConvertOrderAddBean;
import com.fund.bean.FundTradeRedeemAndBuyOrderAddBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack_geng on 2017/7/1.
 */
@Controller
public class FundTradeRedeemAndBuyOrderAdd extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundTradeRedeemAndBuyOrderAdd")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean,
                       @ModelAttribute("ftraboaBean") FundTradeRedeemAndBuyOrderAddBean ftraboaBean,
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
        if (!ftraboaBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + ftraboaBean.getMember_id());
        }
        if (!ftraboaBean.getShare_id().isEmpty()) {
            inputString.append(",share_id=" + ftraboaBean.getShare_id());
        }
        if (!ftraboaBean.getTrade_account_no().isEmpty()){
            inputString.append(",trade_account_no=" + ftraboaBean.getTrade_account_no());
        }
        if (!ftraboaBean.getFund_code().isEmpty()){
            inputString.append(",fund_code=" + ftraboaBean.getFund_code());
        }
        if (!ftraboaBean.getOut_request_no().isEmpty()) {
            inputString.append(",out_request_no=" + ftraboaBean.getOut_request_no());
        }
        if (!ftraboaBean.getDest_fund_code().isEmpty()) {
            inputString.append(",dest_fund_code=" + ftraboaBean.getDest_fund_code());
        }
        if (!ftraboaBean.getTrade_share().isEmpty()) {
            inputString.append(",trade_share=" + ftraboaBean.getTrade_share());
        }
        if (!ftraboaBean.getLarge_flag().isEmpty()) {
            inputString.append(",large_flag=" + ftraboaBean.getLarge_flag());
        }
        if (!ftraboaBean.getOverstep_risk_level().isEmpty()) {
            inputString.append(",overstep_risk_level=" + ftraboaBean.getOverstep_risk_level());
        }
        if (!ftraboaBean.getRisk_notice().isEmpty()) {
            inputString.append(",risk_notice=" + ftraboaBean.getRisk_notice());
        }
        if (!ftraboaBean.getClient_ip().isEmpty()) {
            inputString.append(",client_ip=" + ftraboaBean.getClient_ip());
        }
        if (!ftraboaBean.getMac_address().isEmpty()) {
            inputString.append(",mac_address=" + ftraboaBean.getMac_address());
        }
        if (!ftraboaBean.getPayment_type().isEmpty()) {
            inputString.append(",payment_type=" + ftraboaBean.getPayment_type());
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

        ftraboaBean.setEncodeString(sortString);
        if (!ftraboaBean.getRisk_notice().isEmpty()) {
            ftraboaBean.setEncodeString(tool.removeFromString(ftraboaBean.getEncodeString(), "&", "&", "startswith", "risk_notice") + "&risk_notice=" + tool.textEncode(ftraboaBean.getRisk_notice(), "UTF-8"));
        }
        String requestString = ftraboaBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
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
