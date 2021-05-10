package com.controller;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.mas.bean.FileHostingCollectTradeBean;
import com.mas.bean.MasBaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Controller
public class FileHostingCollectTrade extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FileHostingCollectTrade")
    public void doPost(@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
                       @ModelAttribute("chctBean") FileHostingCollectTradeBean chctBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
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
            inputString
                    .append(",request_time=" + masBaseBean.getRequest_time());
        }
        if (!masBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + masBaseBean.getPartner_id());
        }
        if (!masBaseBean.get_input_charset().isEmpty()) {
            inputString.append(",_input_charset="
                    + masBaseBean.get_input_charset());
        }
        if (!masBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + masBaseBean.getSign_type());
        }
        if (!masBaseBean.getSign_version().isEmpty()) {
            inputString
                    .append(",sign_version=" + masBaseBean.getSign_version());
        }
        if (!masBaseBean.getEncrypt_version().isEmpty()) {
            inputString.append(",encrypt_version="
                    + masBaseBean.getEncrypt_version());
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

        if (!chctBean.getOut_batch_no().isEmpty()) {
            inputString.append(",out_batch_no=" + chctBean.getOut_batch_no());
        }

        if (!chctBean.getSummary().isEmpty()) {
            inputString.append(",summary=" + chctBean.getSummary());
        }
        if (!chctBean.getTrade_close_time().isEmpty()) {
            inputString.append(",trade_close_time="
                    + chctBean.getTrade_close_time());
        }
        if (!chctBean.getUser_ip().isEmpty()) {
            inputString.append(",user_ip=" + chctBean.getUser_ip());
        }

        if (!chctBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + chctBean.getExtend_param());
        }
        if (!chctBean.getFile_path().isEmpty()) {
            inputString.append(",file_path=" + chctBean.getFile_path());
        }
        if (!chctBean.getFile_digest().isEmpty()) {
            inputString.append(",file_digest=" + chctBean.getFile_digest());
        }

        if (!chctBean.getTotal_amount().isEmpty()) {
            inputString.append(",total_amount=" + chctBean.getTotal_amount());
        }
        if (!chctBean.getTotal_quantity().isEmpty()) {
            inputString.append(",total_quantity=" + chctBean.getTotal_quantity());
        }
        if (!chctBean.getOut_trade_code().isEmpty()) {
            inputString.append(",out_trade_code=" + chctBean.getOut_trade_code());
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

        chctBean.setEncodeString(sortString);
        if (!masBaseBean.getNotify_url().isEmpty()) {
            chctBean.setEncodeString(tool.removeFromString(
                    chctBean.getEncodeString(), "&", "&", "startswith",
                    "notify_url")
                    + "&notify_url="
                    + tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!masBaseBean.getReturn_url().isEmpty()) {
            chctBean.setEncodeString(tool.removeFromString(
                    chctBean.getEncodeString(), "&", "&", "startswith",
                    "return_url")
                    + "&return_url="
                    + tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
        }
        if (!chctBean.getSummary().isEmpty()) {
            chctBean.setEncodeString(tool.removeFromString(
                    chctBean.getEncodeString(), "&", "&", "startswith",
                    "summary")
                    + "&summary="
                    + tool.textEncode(chctBean.getSummary(), "UTF-8"));
        }


        String requestString = chctBean.getEncodeString().replaceAll(
                "replaceFlag", ",")
                + "&sign=" + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.textDncode(
                tool.post(masBaseBean.getUrl(), requestString), "UTF-8");
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
