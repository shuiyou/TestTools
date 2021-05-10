package com.controller;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.pas.bean.PasBaseBean;
import com.pas.bean.ValidateEducationBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author sunjie
 *
 */
@Controller
public class ValidateEducation extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "ValidateEducation")
    public void doPost(@ModelAttribute("PasBaseBean") PasBaseBean pasBaseBean,
                       @ModelAttribute("veBean") ValidateEducationBean veBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.print("1111");
        RSAUtil rsa = new RSAUtil();
        MD5Util md5 = new MD5Util();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        pasBaseBean.set_input_charset(request.getParameter("_input_charset"));

        StringBuilder inputString = new StringBuilder("");
        if (!pasBaseBean.getService().isEmpty()) {
            inputString.append("service=" + pasBaseBean.getService());
        }
        if (!pasBaseBean.getVersion().isEmpty()) {
            inputString.append(",version=" + pasBaseBean.getVersion());
        }
        if (!pasBaseBean.getRequest_time().isEmpty()) {
            inputString
                    .append(",request_time=" + pasBaseBean.getRequest_time());
        }
        if (!pasBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + pasBaseBean.getPartner_id());
        }
        if (!pasBaseBean.get_input_charset().isEmpty()) {
            inputString.append(",_input_charset="
                    + pasBaseBean.get_input_charset());
        }
        if (!pasBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + pasBaseBean.getSign_type());
        }
        if (!pasBaseBean.getSign_version().isEmpty()) {
            inputString
                    .append(",sign_version=" + pasBaseBean.getSign_version());
        }
        if (!pasBaseBean.getEncrypt_version().isEmpty()) {
            inputString.append(",encrypt_version="
                    + pasBaseBean.getEncrypt_version());
        }
        if (!pasBaseBean.getNotify_url().isEmpty()) {
            inputString.append(",notify_url=" + pasBaseBean.getNotify_url());
        }
        if (!pasBaseBean.getReturn_url().isEmpty()) {
            inputString.append(",return_url=" + pasBaseBean.getReturn_url());
        }
        if (!pasBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + pasBaseBean.getMemo());
        }
        if (!veBean.getOut_request_no().isEmpty()) {
            inputString.append(",out_request_no=" + veBean.getOut_request_no());
        }

        if (!veBean.getCert_type().isEmpty()) {
            inputString.append(",cert_type=" + veBean.getCert_type());
        }

        if (!veBean.getExtend_param().isEmpty()) {
            inputString.append(",cert_type=" + veBean.getExtend_param());
        }
        String encryptRealName = null;
        if (!veBean.getReal_name().isEmpty()) {
            try {
                encryptRealName = rsa.encrypt(veBean.getReal_name(),
                        pasBaseBean.getEncryptkey());
                inputString.append(",real_name=" + encryptRealName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String encryptCertNo = null;
        if (!veBean.getCert_no().isEmpty()) {
            try {
                encryptCertNo = rsa.encrypt(veBean.getCert_no(),
                        pasBaseBean.getEncryptkey());
                inputString.append(",cert_no=" + encryptCertNo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&",
                "startswith", "sign");

        String sign = null;
        if (pasBaseBean.getSign_type().equals("RSA")) {
            rsa.setPrivateKey(pasBaseBean.getRsakey());
            try {
                sign = rsa.sign(signString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (pasBaseBean.getSign_type().equals("MD5")) {
            String md5SignString = signString + pasBaseBean.getMd5key();
            sign = md5.getMD5(md5SignString, "UTF-8");
        }

        veBean.setEncodeString(sortString);
        if (encryptRealName != null) {
            veBean.setEncodeString(tool.removeFromString(
                    veBean.getEncodeString(), "&", "&", "startswith",
                    "real_name")
                    + "&real_name="
                    + tool.textEncode(encryptRealName, "UTF-8"));
        }
        if (encryptCertNo != null) {
            veBean.setEncodeString(tool.removeFromString(
                    veBean.getEncodeString(), "&", "&", "startswith",
                    "cert_no")
                    + "&cert_no="
                    + tool.textEncode(encryptCertNo, "UTF-8"));
        }


        if (!pasBaseBean.getNotify_url().isEmpty()) {
            veBean.setEncodeString(tool.removeFromString(
                    veBean.getEncodeString(), "&", "&", "startswith",
                    "notify_url")
                    + "&notify_url="
                    + tool.textEncode(pasBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!pasBaseBean.getReturn_url().isEmpty()) {
            veBean.setEncodeString(tool.removeFromString(
                    veBean.getEncodeString(), "&", "&", "startswith",
                    "return_url")
                    + "&return_url="
                    + tool.textEncode(pasBaseBean.getReturn_url(), "UTF-8"));
        }

        String requestString = veBean.getEncodeString() + "&sign="
                + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.textDncode(
                tool.post(pasBaseBean.getUrl(), requestString), "UTF-8");
        System.out.println(responseString);

        request.setAttribute("response", responseString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }

}
