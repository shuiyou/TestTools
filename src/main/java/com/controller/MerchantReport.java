package com.controller;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.mgs.bean.MerchantReportBean;
import com.mgs.bean.MgsBaseBean;
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
 * @author ViannaZhou
 * 商户信息报备
 * 2018-7-17
 */
@Controller
public class MerchantReport extends HttpServlet {

    @RequestMapping(value = "MerchantReport")
    public void doPost(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
                       @ModelAttribute("camBean") MerchantReportBean sppBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        mgsBaseBean.set_input_charset(request.getParameter("_input_charset"));

        /*  参数非空判断 */
        /*  公共参数 */
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
        
        if (!sppBean.getMerchant_identitiy_type().isEmpty()) {
        	inputString.append(",merchant_identitiy_type=" + sppBean.getMerchant_identitiy_type());
        }
        
        if (!sppBean.getMerchant_identity_id().isEmpty()) {
        	inputString.append(",merchant_identity_id=" + sppBean.getMerchant_identity_id());
        }

        /*  接口参数 */
        /*  商户名称 */
        if (!sppBean.getMerchant_name().isEmpty()) {
            inputString.append(",merchant_name=" + sppBean.getMerchant_name());
        }

        /*  商户简称 */
        if (!sppBean.getShort_name().isEmpty()) {
            inputString.append(",short_name=" + sppBean.getShort_name());
        }
        /*  客服电话 */
        if (!sppBean.getCustom_telephone().isEmpty()) {
            inputString.append(",custom_telephone=" + sppBean.getCustom_telephone());
        }
        /*  联系人姓名 */
        if (!sppBean.getContact_name().isEmpty()) {
            inputString.append(",contact_name=" + sppBean.getContact_name());
        }
        /*  联系人业务标识 */
        if (!sppBean.getContact_identity().isEmpty()) {
            inputString.append(",contact_identity=" + sppBean.getContact_identity());
        }
        /*  联系人类型 */
        if (!sppBean.getContact_type().isEmpty()) {
            inputString.append(",contact_type=" + sppBean.getContact_type());
        }
        /*  请求者IP */
        if (!sppBean.getClient_ip().isEmpty()) {
            inputString.append(",client_ip=" + sppBean.getClient_ip());
        }
        /*  APP ID */
        if (!sppBean.getApp_id().isEmpty()) {
            inputString.append(",app_id=" + sppBean.getApp_id());
        }

        /*  扩展信息 */
        if (!sppBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + sppBean.getExtend_param());
        }

        System.out.println(inputString.toString());

        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&",
                "startswith", "sign").replace("#", ",");

        System.out.println("------------加密前字符串" + signString.toString());

        /*  加密 */
        String sign = null;
        RSAUtil rsa = new RSAUtil();
        MD5Util md5 = new MD5Util();
        if (mgsBaseBean.getSign_type().equals("RSA")) {
            rsa.setPrivateKey(mgsBaseBean.getRsakey());
            try {
                sign = rsa.sign(signString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("------------加密后字符串" + sign.toString());

        if (mgsBaseBean.getSign_type().equals("MD5")) {
            String md5SignString = signString + mgsBaseBean.getMd5key();
            sign = md5.getMD5(md5SignString, "UTF-8");
        }

        sppBean.setEncodeString(sortString);

        if (!mgsBaseBean.getNotify_url().isEmpty()) {
            sppBean.setEncodeString(tool.removeFromString(
                    sppBean.getEncodeString(), "&", "&", "startswith",
                    "notify_url")
                    + "&notify_url="
                    + tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!mgsBaseBean.getReturn_url().isEmpty()) {
            sppBean.setEncodeString(tool.removeFromString(
                    sppBean.getEncodeString(), "&", "&", "startswith",
                    "return_url")
                    + "&return_url="
                    + tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
        }

        String requestString = sppBean.getEncodeString() + "&sign="
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
