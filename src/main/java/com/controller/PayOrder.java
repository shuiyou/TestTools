package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mas.bean.PayOrderBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b2c.bean.B2CBaseBean;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
/*
 * **
 * **
 */

@Controller
public class PayOrder extends HttpServlet {

    /*
     * **
     * **
     */

    private static final long serialVersionUID = 1L;
    @RequestMapping(value = "PayOrder")
    public void doPost(@ModelAttribute("b2cBaseBean") B2CBaseBean b2cBaseBean,
                       @ModelAttribute("PayOrderBean") PayOrderBean cboBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        b2cBaseBean.set_input_charset(request.getParameter("_input_charset"));
        StringBuilder inputString = new StringBuilder("");

        if (!b2cBaseBean.getService().isEmpty()) {
            inputString.append("service=" + b2cBaseBean.getService());
        }
        if (!b2cBaseBean.getVersion().isEmpty()) {
            inputString.append(",version=" + b2cBaseBean.getVersion());
        }
        if (!b2cBaseBean.getRequest_time().isEmpty()) {
            inputString
                    .append(",request_time=" + b2cBaseBean.getRequest_time());
        }
        if (!b2cBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + b2cBaseBean.getPartner_id());
        }
        if (!b2cBaseBean.get_input_charset().isEmpty()) {
            inputString.append(",_input_charset="
                    + b2cBaseBean.get_input_charset());
        }
        if (!b2cBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + b2cBaseBean.getSign_type());
        }
        if (!b2cBaseBean.getSign_version().isEmpty()) {
            inputString
                    .append(",sign_version=" + b2cBaseBean.getSign_version());
        }
        if (!b2cBaseBean.getEncrypt_version().isEmpty()) {
            inputString.append(",encrypt_version="
                    + b2cBaseBean.getEncrypt_version());
        }
        if (!b2cBaseBean.getNotify_url().isEmpty()) {
            inputString.append(",notify_url=" + b2cBaseBean.getNotify_url());
        }
        if (!b2cBaseBean.getReturn_url().isEmpty()) {
            inputString.append(",return_url=" + b2cBaseBean.getReturn_url());
        }
        if (!b2cBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + b2cBaseBean.getMemo());
        }
        if (!cboBean.getOut_pay_no().isEmpty()) {
            inputString.append(",out_pay_no=" + cboBean.getOut_pay_no());
        }
        if (!cboBean.getOut_trade_no().isEmpty()) {
            inputString.append(",out_trade_no="
                    + cboBean.getOut_trade_no());
        }
        if (!cboBean.getDevice_id().isEmpty()) {
            inputString.append(",device_id=" + cboBean.getDevice_id());
        }

        String riskInfo = null;
        if (!b2cBaseBean.getRisk_info().isEmpty()) {
            riskInfo = b2cBaseBean.getRisk_info().replaceAll(",","replaceFlag");
            inputString.append(",risk_info=" + riskInfo);
        }
        String deviceInfo = null;
        if (!b2cBaseBean.getDevice_info().isEmpty()) {
            deviceInfo = b2cBaseBean.getDevice_info().replaceAll(",","replaceFlag");
            inputString.append(",device_info=" + deviceInfo);
        }



        if (!cboBean.getPayer_ip().isEmpty()) {
            inputString.append(",payer_ip=" + cboBean.getPayer_ip());
        }
        if (!cboBean.getCashdesk_addr_category().isEmpty()) {
            inputString.append(",cashdesk_addr_category=" + cboBean.getCashdesk_addr_category());
        }
        String quickPayString = null;
        if (!cboBean.getPay_method().isEmpty()
                && cboBean.getPay_method().startsWith("quick_pay")) {
            Tools tool = new Tools();
            quickPayString = tool.quickPayEncrypt(cboBean.getPay_method(),
                    b2cBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
            inputString.append(",pay_method=" + quickPayString);
        } else if (!cboBean.getPay_method().isEmpty()
                && !cboBean.getPay_method().startsWith("quick_pay")) {
            quickPayString = cboBean.getPay_method().replaceAll(",",
                    "replaceFlag");
            inputString.append(",pay_method=" + quickPayString);
        }

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&",
                "startswith", "sign").replaceAll("replaceFlag", ",");
        System.out.println(signString);

        String sign = null;
        RSAUtil rsa = new RSAUtil();
        MD5Util md5 = new MD5Util();
        if (b2cBaseBean.getSign_type().equals("RSA")) {
            rsa.setPrivateKey(b2cBaseBean.getRsakey());
            try {
                sign = rsa.sign(signString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (b2cBaseBean.getSign_type().equals("MD5")) {
            String md5SignString = signString + b2cBaseBean.getMd5key();
            sign = md5.getMD5(md5SignString, "UTF-8");
        }

        cboBean.setEncodeString(sortString);
        if (!b2cBaseBean.getNotify_url().isEmpty()) {
            cboBean.setEncodeString(tool.removeFromString(
                    cboBean.getEncodeString(), "&", "&", "startswith",
                    "notify_url")
                    + "&notify_url="
                    + tool.textEncode(b2cBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!b2cBaseBean.getReturn_url().isEmpty()) {
            cboBean.setEncodeString(tool.removeFromString(
                    cboBean.getEncodeString(), "&", "&", "startswith",
                    "return_url")
                    + "&return_url="
                    + tool.textEncode(b2cBaseBean.getReturn_url(), "UTF-8"));
        }

        if (!cboBean.getPay_method().isEmpty()) {
            cboBean.setEncodeString(tool.removeFromString(
                    cboBean.getEncodeString(), "&", "&", "startswith",
                    "pay_method")
                    + "&pay_method="
                    + tool.textEncode(quickPayString, "UTF-8"));
        }
        if (!b2cBaseBean.getRisk_info().isEmpty()) {
            cboBean.setEncodeString(tool.removeFromString(
                    cboBean.getEncodeString(), "&", "&", "startswith",
                    "risk_info")
                    + "&risk_info="
                    + tool.textEncode(riskInfo, "UTF-8"));
        }

        String requestString = cboBean.getEncodeString().replaceAll(
                "replaceFlag", ",")
                + "&sign=" + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.textDncode(
                tool.post(b2cBaseBean.getUrl(), requestString), "UTF-8");
        System.out.println(responseString);

        request.setAttribute("response", responseString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }

}
