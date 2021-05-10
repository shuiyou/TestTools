package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundMemberAddBean;
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
 * Created by sunjie on 2017/6/2.
 */
@Controller
public class FundMemberAdd extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundMemberAdd")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean, @ModelAttribute("fmaBean") FundMemberAddBean fmaBean, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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
        if (!fmaBean.getLogin_name().isEmpty()) {
            inputString.append(",login_name=" + fmaBean.getLogin_name());
        }
        if (!fmaBean.getLogin_name_type().isEmpty()) {
            inputString.append(",login_name_type=" + fmaBean.getLogin_name_type());
        }
        String accountName = null;
        if (!fmaBean.getAccount_name().isEmpty()) {
            try {
                accountName = rsa.encrypt(fmaBean.getAccount_name(), fundBaseBean.getEncryptkey());
                inputString.append(",account_name=" + accountName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String phoneNo = null;
        if (!fmaBean.getPhone_no().isEmpty()) {
            try {
                phoneNo = rsa.encrypt(fmaBean.getPhone_no(), fundBaseBean.getEncryptkey());
                inputString.append(",phone_no=" + phoneNo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

//        String eMail = null;
//        if (!fmaBean.getEmail().isEmpty()) {
//            try {
//                eMail = rsa.encrypt(fmaBean.getEmail(), fundBaseBean.getEncryptkey());
//                inputString.append(",email=" + eMail);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        String certNo = null;
        if (!fmaBean.getCert_no().isEmpty()) {
            try {
                certNo = rsa.encrypt(fmaBean.getCert_no(), fundBaseBean.getEncryptkey());
                inputString.append(",cert_no=" + certNo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (!fmaBean.getCert_type().isEmpty()) {
            inputString.append(",cert_type=" + fmaBean.getCert_type());
        }
        if (!fmaBean.getMember_type().isEmpty()) {
            inputString.append(",member_type=" + fmaBean.getMember_type());
        }
        if (!fmaBean.getProfessional_type().isEmpty()) {
            inputString.append(",professional_type=" + fmaBean.getProfessional_type());
        }
        if (!fmaBean.getTax_type().isEmpty()) {
            inputString.append(",tax_type=" + fmaBean.getTax_type());
        }
        if (!fmaBean.getSurname().isEmpty()) {
            inputString.append(",surname=" + fmaBean.getSurname());
        }
        if (!fmaBean.getName().isEmpty()) {
            inputString.append(",name=" + fmaBean.getName());
        }
        if (!fmaBean.getBirthday().isEmpty()) {
            inputString.append(",birthday=" + fmaBean.getBirthday());
        }
        if (!fmaBean.getResidential_address_chs().isEmpty()) {
            inputString.append(",residential_address_chs=" + fmaBean.getResidential_address_chs());
        }
        if (!fmaBean.getResidential_address_en().isEmpty()) {
            inputString.append(",residential_address_en=" + fmaBean.getResidential_address_en());
        }
        if (!fmaBean.getBirthplace_chs().isEmpty()) {
            inputString.append(",birthplace_chs=" + fmaBean.getBirthplace_chs());
        }
        if (!fmaBean.getBirthplace_en().isEmpty()) {
            inputString.append(",birthplace_en=" + fmaBean.getBirthplace_en());
        }
        if (!fmaBean.getTime_limited().isEmpty()) {
            inputString.append(",time_limited=" + fmaBean.getTime_limited());
        }
        if (!fmaBean.getGender().isEmpty()) {
            inputString.append(",gender=" + fmaBean.getGender());
        }
        if (!fmaBean.getExist_not_resident_control().isEmpty()) {
            inputString.append(",exist_not_resident_control=" + fmaBean.getExist_not_resident_control());
        }
        if (!fmaBean.getReside_country().isEmpty()) {
            inputString.append(",reside_country=" + fmaBean.getReside_country());
        }
        if (!fmaBean.getBorn_country().isEmpty()) {
            inputString.append(",born_country=" + fmaBean.getBorn_country());
        }
        if (!fmaBean.getBorn_country_en().isEmpty()) {
            inputString.append(",born_country_en=" + fmaBean.getBorn_country_en());
        }
        if (!fmaBean.getMember_tax_info_list().isEmpty()) {
            inputString.append(",member_tax_info_list=" + fmaBean.getMember_tax_info_list().replace(",","replaceFlag"));
        }


        System.out.println("inputString"+inputString.toString());

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&").replace("replaceFlag",",");
        String signString = tool.removeFromString(sortString, "&", "&", "startswith", "sign").replace("#", ",");
        System.out.println("signString"+signString);
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

        fmaBean.setEncodeString(sortString);
        if (!fmaBean.getAccount_name().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "account_name") + "&account_name=" + tool.textEncode(accountName, "UTF-8"));
        }
        if (!fmaBean.getPhone_no().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "phone_no") + "&phone_no=" + tool.textEncode(phoneNo, "UTF-8"));
        }
        if (!fmaBean.getCert_no().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "cert_no") + "&cert_no=" + tool.textEncode(certNo, "UTF-8"));
        }
        if (!fmaBean.getSurname().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "surname") + "&surname=" + tool.textEncode(fmaBean.getSurname(), "UTF-8"));
        }
        if (!fmaBean.getName().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "name") + "&name=" + tool.textEncode(fmaBean.getName(), "UTF-8"));
        }
        if (!fmaBean.getResidential_address_chs().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "residential_address_chs") + "&residential_address_chs=" + tool.textEncode(fmaBean.getResidential_address_chs(), "UTF-8"));
        }
        if (!fmaBean.getBirthplace_chs().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "birthplace_chs") + "&birthplace_chs=" + tool.textEncode(fmaBean.getBirthplace_chs(), "UTF-8"));
        }
        if (!fmaBean.getMember_tax_info_list().isEmpty()) {
            fmaBean.setEncodeString(tool.removeFromString(fmaBean.getEncodeString(), "&", "&", "startswith", "member_tax_info_list") + "&member_tax_info_list=" + tool.textEncode(fmaBean.getMember_tax_info_list(), "UTF-8"));
        }

        String requestString = fmaBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
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

