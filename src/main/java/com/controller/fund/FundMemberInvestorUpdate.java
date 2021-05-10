package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundMemberInvestorUpdateBean;
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
 * Created by yimeiweile on 2017/6/21.
 */
@Controller
public class FundMemberInvestorUpdate extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundMemberInvestorUpdate")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean,
                       @ModelAttribute("fmiuBean") FundMemberInvestorUpdateBean fmiuBean,
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
            inputString
                    .append(",request_time=" + fundBaseBean.getRequest_time());
        }
        if (!fundBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + fundBaseBean.getPartner_id());
        }
        if (!fundBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + fundBaseBean.getSign_type());
        }
        if (!fundBaseBean.getSign_version().isEmpty()) {
            inputString
                    .append(",sign_version=" + fundBaseBean.getSign_version());
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
        if (!fmiuBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + fmiuBean.getMember_id());
        }
        if (!fmiuBean.getAddress_info().isEmpty()) {
            inputString.append(",address_info=" + fmiuBean.getAddress_info());
        }
        if (!fmiuBean.getOccupation().isEmpty()) {
            inputString.append(",occupation=" + fmiuBean.getOccupation());
        }
        if (!fmiuBean.getDuties().isEmpty()) {
            inputString.append(",duties=" + fmiuBean.getDuties());
        }
        if (!fmiuBean.getIncome_source().isEmpty()) {
            inputString.append(",income_source=" + fmiuBean.getIncome_source());
        }
        if (!fmiuBean.getAssets_debt_info().isEmpty()) {
            inputString.append(",assets_debt_info=" + fmiuBean.getAssets_debt_info());
        }
        if (!fmiuBean.getZip_code().isEmpty()) {
            inputString.append(",zip_code=" + fmiuBean.getZip_code());
        }
        if (!fmiuBean.getEmail().isEmpty()) {
            inputString.append(",email=" + fmiuBean.getEmail());
        }
        if (!fmiuBean.getBeneficiary().isEmpty()) {
            inputString.append(",beneficiary=" + fmiuBean.getBeneficiary());
        }
        if (!fmiuBean.getOperator().isEmpty()) {
            inputString.append(",operator=" + fmiuBean.getOperator());
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
        fmiuBean.setEncodeString(sortString);

        if (!fmiuBean.getAddress_info().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "address_info") + "&address_info=" + tool.textEncode(fmiuBean.getAddress_info(), "UTF-8"));
        }

        if (!fmiuBean.getOccupation().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "occupation") + "&occupation=" + tool.textEncode(fmiuBean.getOccupation(), "UTF-8"));
        }
        if (!fmiuBean.getDuties().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "duties") + "&duties=" + tool.textEncode(fmiuBean.getDuties(), "UTF-8"));
        }
        if (!fmiuBean.getIncome_source().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "income_source") + "&income_source=" + tool.textEncode(fmiuBean.getIncome_source(), "UTF-8"));
        }
        if (!fmiuBean.getAssets_debt_info().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "assets_debt_info") + "&assets_debt_info=" + tool.textEncode(fmiuBean.getAssets_debt_info(), "UTF-8"));
        }
        if (!fmiuBean.getZip_code().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "zip_code") + "&zip_code=" + tool.textEncode(fmiuBean.getZip_code(), "UTF-8"));
        }
        if (!fmiuBean.getEmail().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "email") + "&email=" + tool.textEncode(fmiuBean.getEmail(), "UTF-8"));
        }
        if (!fmiuBean.getBeneficiary().isEmpty()) {
            fmiuBean.setEncodeString(tool.removeFromString(fmiuBean.getEncodeString(), "&", "&", "startswith", "beneficiary") + "&beneficiary=" + tool.textEncode(fmiuBean.getBeneficiary(), "UTF-8"));
        }




        String requestString = fmiuBean.getEncodeString() + "&sign="
                + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        /*String responseString = tool.textDncode(
                tool.post(fundBaseBean.getUrl(), requestString), "UTF-8");*/
        String responseString = tool.post(fundBaseBean.getUrl(), requestString);
        System.out.println(responseString);
        String dataString = tool.base64Decode(responseString.substring(responseString.indexOf("data=") + 5, responseString.indexOf("&sign")));
        System.out.println(dataString);

        request.setAttribute("response", dataString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }

}





