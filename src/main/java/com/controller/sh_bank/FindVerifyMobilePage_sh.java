package com.controller.sh_bank;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.mas.bean.MasBaseBean;
import com.sh_bank.bean.FindVerifyMobilePage_shBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
/**
 * Created by Administrator on 2017/8/22.
 */
public class FindVerifyMobilePage_sh extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FindVerifyMobilePage_sh")
    public void doPost(@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
                       @ModelAttribute("chrBean") FindVerifyMobilePage_shBean chrBean, HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        masBaseBean.set_input_charset(request.getParameter("_input_charset"));

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
        if (!chrBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + chrBean.getExtend_param());
        }



        String[] splitString = inputString.toString().split(",");

        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");
        String signString = tool.removeFromString(sortString, "&", "&", "startswith", "sign").replaceAll("replaceFlag",
                ",");
        System.out.println(signString);

        String sign = null;
        RSAUtil rsa = new RSAUtil();
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
        System.out.println("????????????: " + requestString);

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
