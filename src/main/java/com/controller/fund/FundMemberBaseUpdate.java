package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundBaseBean;
import com.fund.bean.FundMemberAddBean;
import com.fund.bean.FundMemberBaseUpdateBean;
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
public class FundMemberBaseUpdate extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundMemberBaseUpdate")
    public void doPost(@ModelAttribute("FundMemberBaseUpdate") FundBaseBean fundBaseBean, @ModelAttribute("fmbuBean") FundMemberBaseUpdateBean fmbuBean, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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


        if (!fmbuBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + fmbuBean.getMember_id());
        }
        if (!fmbuBean.getTax_type().isEmpty()) {
            inputString.append(",tax_type=" + fmbuBean.getTax_type());
        }
        if (!fmbuBean.getSurname().isEmpty()) {
            inputString.append(",surname=" + fmbuBean.getSurname());
        }
        if (!fmbuBean.getName().isEmpty()) {
            inputString.append(",name=" + fmbuBean.getName());
        }
        if (!fmbuBean.getBirthday().isEmpty()) {
            inputString.append(",birthday=" + fmbuBean.getBirthday());
        }
        if (!fmbuBean.getResidential_address_chs().isEmpty()) {
            inputString.append(",residential_address_chs=" + fmbuBean.getResidential_address_chs());
        }
        if (!fmbuBean.getResidential_address_en().isEmpty()) {
            inputString.append(",residential_address_en=" + fmbuBean.getResidential_address_en());
        }
        if (!fmbuBean.getBirthplace_chs().isEmpty()) {
            inputString.append(",birthplace_chs=" + fmbuBean.getBirthplace_chs());
        }
        if (!fmbuBean.getBirthplace_en().isEmpty()) {
            inputString.append(",birthplace_en=" + fmbuBean.getBirthplace_en());
        }
        if (!fmbuBean.getTime_limited().isEmpty()) {
            inputString.append(",time_limited=" + fmbuBean.getTime_limited());
        }
        if (!fmbuBean.getGender().isEmpty()) {
            inputString.append(",gender=" + fmbuBean.getGender());
        }
        if (!fmbuBean.getExist_not_resident_control().isEmpty()) {
            inputString.append(",exist_not_resident_control=" + fmbuBean.getExist_not_resident_control());
        }
        if (!fmbuBean.getReside_country().isEmpty()) {
            inputString.append(",reside_country=" + fmbuBean.getReside_country());
        }
        if (!fmbuBean.getBorn_country().isEmpty()) {
            inputString.append(",born_country=" + fmbuBean.getBorn_country());
        }
        if (!fmbuBean.getBorn_country_en().isEmpty()) {
            inputString.append(",born_country_en=" + fmbuBean.getBorn_country_en());
        }
        if (!fmbuBean.getMember_tax_info_list().isEmpty()) {
            inputString.append(",member_tax_info_list=" + fmbuBean.getMember_tax_info_list().replace(",","replaceFlag"));
        }


        System.out.println("inputString"+inputString.toString());

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&").replace("replaceFlag", ",");
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

        fmbuBean.setEncodeString(sortString);

        if (!fmbuBean.getSurname().isEmpty()) {
            fmbuBean.setEncodeString(tool.removeFromString(fmbuBean.getEncodeString(), "&", "&", "startswith", "surname") + "&surname=" + tool.textEncode(fmbuBean.getSurname(), "UTF-8"));
        }
        if (!fmbuBean.getName().isEmpty()) {
            fmbuBean.setEncodeString(tool.removeFromString(fmbuBean.getEncodeString(), "&", "&", "startswith", "name") + "&name=" + tool.textEncode(fmbuBean.getName(), "UTF-8"));
        }
        if (!fmbuBean.getResidential_address_chs().isEmpty()) {
            fmbuBean.setEncodeString(tool.removeFromString(fmbuBean.getEncodeString(), "&", "&", "startswith", "residential_address_chs") + "&residential_address_chs=" + tool.textEncode(fmbuBean.getResidential_address_chs(), "UTF-8"));
        }
        if (!fmbuBean.getBirthplace_chs().isEmpty()) {
            fmbuBean.setEncodeString(tool.removeFromString(fmbuBean.getEncodeString(), "&", "&", "startswith", "birthplace_chs") + "&birthplace_chs=" + tool.textEncode(fmbuBean.getBirthplace_chs(), "UTF-8"));
        }
        if (!fmbuBean.getMember_tax_info_list().isEmpty()) {
            fmbuBean.setEncodeString(tool.removeFromString(fmbuBean.getEncodeString(), "&", "&", "startswith", "member_tax_info_list") + "&member_tax_info_list=" + tool.textEncode(fmbuBean.getMember_tax_info_list(), "UTF-8"));
        }

        String requestString = fmbuBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
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

