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
import com.sh_bank.bean.BindingBankCard_shBean;
import com.sh_bank.bean.ShMgsBaseBean;


/**
 *
 * @author sunjie
 *
 */
@Controller
public class BindingBankCard_sh extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "BindingBankCard_sh")
    public void doPost(@ModelAttribute("mgsBaseBean") ShMgsBaseBean mgsBaseBean,
                       @ModelAttribute("bbcBean") BindingBankCard_shBean bbcBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RSAUtil rsa = new RSAUtil();
        MD5Util md5 = new MD5Util();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        mgsBaseBean.set_input_charset(request.getParameter("_input_charset"));

        StringBuilder inputString = new StringBuilder("");
        if (!mgsBaseBean.getService().isEmpty()) {
            inputString.append("service=" + mgsBaseBean.getService());
        }
        if (!mgsBaseBean.getVersion().isEmpty()) {
            inputString.append(",version=" + mgsBaseBean.getVersion());
        }
        if (!mgsBaseBean.getRequest_time().isEmpty()) {
            inputString
                    .append(",request_time=" + mgsBaseBean.getRequest_time());
        }
        if (!mgsBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + mgsBaseBean.getPartner_id());
        }
        if (!mgsBaseBean.get_input_charset().isEmpty()) {
            inputString.append(",_input_charset="
                    + mgsBaseBean.get_input_charset());
        }
        if (!mgsBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + mgsBaseBean.getSign_type());
        }
        if (!mgsBaseBean.getSign_version().isEmpty()) {
            inputString
                    .append(",sign_version=" + mgsBaseBean.getSign_version());
        }
        if (!mgsBaseBean.getEncrypt_version().isEmpty()) {
            inputString.append(",encrypt_version="
                    + mgsBaseBean.getEncrypt_version());
        }
        if (!mgsBaseBean.getNotify_url().isEmpty()) {
            inputString.append(",notify_url=" + mgsBaseBean.getNotify_url());
        }
        if (!mgsBaseBean.getReturn_url().isEmpty()) {
            inputString.append(",return_url=" + mgsBaseBean.getReturn_url());
        }
        if (!mgsBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + mgsBaseBean.getMemo());
        }
        if (!bbcBean.getRequest_no().isEmpty()) {
            inputString.append(",request_no=" + bbcBean.getRequest_no());
        }
        if (!bbcBean.getIdentity_id().isEmpty()) {
            inputString.append(",identity_id=" + bbcBean.getIdentity_id());
        }
        if (!bbcBean.getIdentity_type().isEmpty()) {
            inputString.append(",identity_type=" + bbcBean.getIdentity_type());
        }
        if (!bbcBean.getCard_id().isEmpty()) {
            inputString.append(",card_id=" + bbcBean.getCard_id());
        }
        if (!bbcBean.getBank_code().isEmpty()) {
            inputString.append(",bank_code=" + bbcBean.getBank_code());
        }

        String encryptBankAccNo = null;
        if (!bbcBean.getBank_account_no().isEmpty()) {
            try {
                encryptBankAccNo = rsa.encrypt(bbcBean.getBank_account_no(),
                        mgsBaseBean.getEncryptkey());
                inputString.append(",bank_account_no=" + encryptBankAccNo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        String encryptAccName = null;
        if (!bbcBean.getAccount_name().isEmpty()) {
            try {
                encryptAccName = rsa.encrypt(bbcBean.getAccount_name(),
                        mgsBaseBean.getEncryptkey());
                inputString.append(",account_name=" + encryptAccName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (!bbcBean.getCard_type().isEmpty()) {
            inputString.append(",card_type=" + bbcBean.getCard_type());
        }
        if (!bbcBean.getCard_attribute().isEmpty()) {
            inputString
                    .append(",card_attribute=" + bbcBean.getCard_attribute());
        }
        if (!bbcBean.getCert_type().isEmpty()) {
            inputString.append(",cert_type=" + bbcBean.getCert_type());
        }
        String encryptCertNo = null;
        if (!bbcBean.getCert_no().isEmpty()) {
            try {
                encryptCertNo = rsa.encrypt(bbcBean.getCert_no(),
                        mgsBaseBean.getEncryptkey());
                inputString.append(",cert_no=" + encryptCertNo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        String encryptPhoneNo = null;
        if (!bbcBean.getPhone_no().isEmpty()) {
            try {
                encryptPhoneNo = rsa.encrypt(bbcBean.getPhone_no(),
                        mgsBaseBean.getEncryptkey());
                inputString.append(",phone_no=" + encryptPhoneNo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String encryptValidityPeriod = null;
        if (!bbcBean.getValidity_period().isEmpty()) {
            try {
                encryptValidityPeriod = rsa.encrypt(
                        bbcBean.getValidity_period(),
                        mgsBaseBean.getEncryptkey());
                inputString.append(",validity_period=" + encryptValidityPeriod);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String encryptCVV2 = null;
        if (!bbcBean.getVerification_value().isEmpty()) {
            try {
                encryptCVV2 = rsa.encrypt(bbcBean.getVerification_value(),
                        mgsBaseBean.getEncryptkey());
                inputString.append(",verification_value=" + encryptCVV2);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        if (!bbcBean.getProvince().isEmpty()) {
            inputString.append(",province=" + bbcBean.getProvince());
        }
        if (!bbcBean.getCity().isEmpty()) {
            inputString.append(",city=" + bbcBean.getCity());
        }
        if (!bbcBean.getBank_branch().isEmpty()) {
            inputString.append(",bank_branch=" + bbcBean.getBank_branch());
        }



        if (!bbcBean.getIs_verified().isEmpty()) {
            inputString.append(",is_verified=" + bbcBean.getIs_verified());
        }
        if (!bbcBean.getAlias().isEmpty()) {
            inputString.append(",alias=" + bbcBean.getAlias());
        }
        if (!bbcBean.getCard_skin().isEmpty()) {
            inputString.append(",card_skin=" + bbcBean.getCard_skin());
        }

        if (!bbcBean.getIs_signing().isEmpty()) {
            inputString.append(",is_signing=" + bbcBean.getIs_signing());
        }
        if (!bbcBean.getStatus().isEmpty()) {
            inputString.append(",status=" + bbcBean.getStatus());
        }
        if (!bbcBean.getPay_attribute().isEmpty()) {
            inputString.append(",pay_attribute=" + bbcBean.getPay_attribute());
        }

        if (!bbcBean.getChannel_code().isEmpty()) {
            inputString.append(",channel_code=" + bbcBean.getChannel_code());
        }
        if (!bbcBean.getSign_num().isEmpty()) {
            inputString.append(",sign_num=" + bbcBean.getSign_num());
        }
        if (!bbcBean.getSign_id().isEmpty()) {
            inputString.append(",sign_id=" + bbcBean.getSign_id());
        }

        if (!bbcBean.getAgreement_valid_date().isEmpty()) {
            inputString.append(",agreement_valid_date=" + bbcBean.getAgreement_valid_date());
        }
        if (!bbcBean.getBank_name().isEmpty()) {
            inputString.append(",bank_name=" + bbcBean.getBank_name());
        }

        if (!bbcBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + bbcBean.getExtend_param());
        }

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&",
                "startswith", "sign");

        String sign = null;
        if (mgsBaseBean.getSign_type().equals("RSA")) {
            rsa.setPrivateKey(mgsBaseBean.getRsakey());
            try {
                sign = rsa.sign(signString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (mgsBaseBean.getSign_type().equals("MD5")) {
            String md5SignString = signString + mgsBaseBean.getMd5key();
            sign = md5.getMD5(md5SignString, "UTF-8");
        }

        bbcBean.setEncodeString(sortString);
        if (encryptBankAccNo != null) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "bank_account_no")
                    + "&bank_account_no="
                    + tool.textEncode(encryptBankAccNo, "UTF-8"));
        }
        if (encryptAccName != null) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "account_name")
                    + "&account_name="
                    + tool.textEncode(encryptAccName, "UTF-8"));
        }
        if (encryptCertNo != null) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "cert_no")
                    + "&cert_no="
                    + tool.textEncode(encryptCertNo, "UTF-8"));
        }
        if (encryptPhoneNo != null) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "phone_no")
                    + "&phone_no="
                    + tool.textEncode(encryptPhoneNo, "UTF-8"));
        }
        if (encryptValidityPeriod != null) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "validity_period")
                    + "&validity_period="
                    + tool.textEncode(encryptValidityPeriod, "UTF-8"));
        }
        if (encryptCVV2 != null) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "verification_value")
                    + "&verification_value="
                    + tool.textEncode(encryptCVV2, "UTF-8"));
        }
        if (!mgsBaseBean.getNotify_url().isEmpty()) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "notify_url")
                    + "&notify_url="
                    + tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!mgsBaseBean.getReturn_url().isEmpty()) {
            bbcBean.setEncodeString(tool.removeFromString(
                    bbcBean.getEncodeString(), "&", "&", "startswith",
                    "return_url")
                    + "&return_url="
                    + tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
        }

        String requestString = bbcBean.getEncodeString() + "&sign="
                + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.textDncode(
                tool.post(mgsBaseBean.getUrl(), requestString), "UTF-8");
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
