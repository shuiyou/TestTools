package com.controller.sh_bank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.sh_bank.bean.InitMemberByProcess_shBean;
import com.sh_bank.bean.ShMgsBaseBean;

/**
 * 
 * @author Lin
 *
 */
@Controller
public class InitMemberByProcess_sh extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "InitMemberByProcess_sh")
    public void doPost(@ModelAttribute("masBaseBean") ShMgsBaseBean masBaseBean,
            @ModelAttribute("chrBean") InitMemberByProcess_shBean chrBean, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        masBaseBean.set_input_charset(request.getParameter("_input_charset"));

        RSAUtil rsa = new RSAUtil();
        String encryptRealName = null;
        String encryptCertNo = null;

        StringBuilder inputString = new StringBuilder("");
        if (!masBaseBean.getService().isEmpty()) {
            inputString.append("service=" + masBaseBean.getService());
        }
        if (!masBaseBean.getVersion().isEmpty()) {
            inputString.append(",version=" + masBaseBean.getVersion());
        }
        if (!masBaseBean.getRequest_time().isEmpty()) {
            inputString.append(",request_time=" + masBaseBean.getRequest_time());
        }
        if (!masBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + masBaseBean.getPartner_id());
        }
        if (!masBaseBean.get_input_charset().isEmpty()) {
            inputString.append(",_input_charset=" + masBaseBean.get_input_charset());
        }
        if (!masBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + masBaseBean.getSign_type());
        }
        if (!masBaseBean.getSign_version().isEmpty()) {
            inputString.append(",sign_version=" + masBaseBean.getSign_version());
        }
        if (!masBaseBean.getEncrypt_version().isEmpty()) {
            inputString.append(",encrypt_version=" + masBaseBean.getEncrypt_version());
        }
        if (!masBaseBean.getNotify_url().isEmpty()) {
            inputString.append(",notify_url=" + masBaseBean.getNotify_url());
        }
        if (!masBaseBean.getReturn_url().isEmpty()) {
            inputString.append(",return_url=" + masBaseBean.getReturn_url());
        }
        if (!masBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + masBaseBean.getMemo());
        }
        if (!chrBean.getIdentity_id().isEmpty()) {
            inputString.append(",identity_id=" + chrBean.getIdentity_id());
        }
        if (!chrBean.getIdentity_type().isEmpty()) {
            inputString.append(",identity_type=" + chrBean.getIdentity_type());
        }
        if (!chrBean.getFlow_category().isEmpty()) {
            inputString.append(",flow_category=" + chrBean.getFlow_category());
        }
        if (!chrBean.getMember_type().isEmpty()) {
            inputString.append(",member_type=" + chrBean.getMember_type());
        }
        if (!chrBean.getHost_role().isEmpty()) {
            inputString.append(",host_role=" + chrBean.getHost_role());
        }
        if (!chrBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + chrBean.getExtend_param());
        }
        if (!chrBean.getReal_name().isEmpty()) {
            try {
                encryptRealName = rsa.encrypt(chrBean.getReal_name(), masBaseBean.getEncryptkey());
                inputString.append(",real_name=" + encryptRealName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (!chrBean.getCert_type().isEmpty()) {
            inputString.append(",cert_type=" + chrBean.getCert_type());
        }
        if (!chrBean.getCert_no().isEmpty()) {
            try {
                encryptCertNo = rsa.encrypt(chrBean.getCert_no(), masBaseBean.getEncryptkey());
                inputString.append(",cert_no=" + encryptCertNo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (!chrBean.getAccount_type().isEmpty()) {
            inputString.append(",account_type=" + chrBean.getAccount_type());
        }

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&", "startswith", "sign");
        System.out.println(signString);

        String sign = null;
        MD5Util md5 = new MD5Util();
        if (masBaseBean.getSign_type().equals("RSA")) {
            rsa.setPrivateKey(masBaseBean.getRsakey());
            try {
                sign = rsa.sign(signString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (masBaseBean.getSign_type().equals("MD5")) {
            String md5SignString = signString + masBaseBean.getMd5key();
            sign = md5.getMD5(md5SignString, "UTF-8");
        }
        chrBean.setEncodeString(sortString);
        if (encryptRealName != null) {
            chrBean.setEncodeString(
                    tool.removeFromString(chrBean.getEncodeString(), "&", "&", "startswith", "real_name")
                            + "&real_name=" + tool.textEncode(encryptRealName, "UTF-8"));
        }
        if (encryptCertNo != null) {
            chrBean.setEncodeString(tool.removeFromString(chrBean.getEncodeString(), "&", "&", "startswith", "cert_no")
                    + "&cert_no=" + tool.textEncode(encryptCertNo, "UTF-8"));
        }

        
        if (!masBaseBean.getNotify_url().isEmpty()) {
            chrBean.setEncodeString(
                    tool.removeFromString(chrBean.getEncodeString(), "&", "&", "startswith", "notify_url")
                            + "&notify_url=" + tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!masBaseBean.getReturn_url().isEmpty()) {
            chrBean.setEncodeString(
                    tool.removeFromString(chrBean.getEncodeString(), "&", "&", "startswith", "return_url")
                            + "&return_url=" + tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
        }

        String requestString = chrBean.getEncodeString().replaceAll("replaceFlag", ",") + "&sign="
                + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.textDncode(tool.post(masBaseBean.getUrl(), requestString), "UTF-8");
        System.out.println(responseString);

        request.setAttribute("response", responseString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

}
