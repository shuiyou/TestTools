package com.controller.sh_bank;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.sh_bank.bean.PayBackApplyPage_shBean;
import com.sh_bank.bean.QueryBalance_shBean;

import com.sh_bank.bean.ShMgsBaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
public class PayBackApplyPage_sh extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "PayBackApplyPage_sh")
    public void doPost(@ModelAttribute("ShMgsBaseBean") ShMgsBaseBean ShMgsBaseBean,
                       @ModelAttribute("chrBean") PayBackApplyPage_shBean chrBean, HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ShMgsBaseBean.set_input_charset(request.getParameter("_input_charset"));

        StringBuilder inputString = new StringBuilder("");
        if (!ShMgsBaseBean.getService().isEmpty()) {
            inputString.append("service=" + ShMgsBaseBean.getService());
        }
        if (!ShMgsBaseBean.getVersion().isEmpty()) {
            inputString.append(",version=" + ShMgsBaseBean.getVersion());
        }
        if (!ShMgsBaseBean.getCashdesk_addr_category().isEmpty()) {
            inputString
                    .append(",cashdesk_addr_category=" + ShMgsBaseBean.getCashdesk_addr_category());
        }
        if (!ShMgsBaseBean.getRequest_time().isEmpty()) {
            inputString.append(",request_time=" + ShMgsBaseBean.getRequest_time());
        }
        if (!ShMgsBaseBean.getPartner_id().isEmpty()) {
            inputString.append(",partner_id=" + ShMgsBaseBean.getPartner_id());
        }
        if (!ShMgsBaseBean.get_input_charset().isEmpty()) {
            inputString.append(",_input_charset=" + ShMgsBaseBean.get_input_charset());
        }
        if (!ShMgsBaseBean.getSign_type().isEmpty()) {
            inputString.append(",sign_type=" + ShMgsBaseBean.getSign_type());
        }
        if (!ShMgsBaseBean.getSign_version().isEmpty()) {
            inputString.append(",sign_version=" + ShMgsBaseBean.getSign_version());
        }
        if (!ShMgsBaseBean.getEncrypt_version().isEmpty()) {
            inputString.append(",encrypt_version=" + ShMgsBaseBean.getEncrypt_version());
        }
        if (!ShMgsBaseBean.getNotify_url().isEmpty()) {
            inputString.append(",notify_url=" + ShMgsBaseBean.getNotify_url());
        }
        if (!ShMgsBaseBean.getReturn_url().isEmpty()) {
            inputString.append(",return_url=" + ShMgsBaseBean.getReturn_url());
        }
        if (!ShMgsBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + ShMgsBaseBean.getMemo());
        }
        if (!chrBean.getIdentity_id().isEmpty()) {
            inputString.append(",identity_id=" + chrBean.getIdentity_id());
        }
        if (!chrBean.getIdentity_type().isEmpty()) {
            inputString.append(",identity_type=" + chrBean.getIdentity_type());
        }
        if (!chrBean.getOut_trade_no().isEmpty()) {
            inputString.append(",out_trade_no=" + chrBean.getOut_trade_no());
        }
        if (!chrBean.getOut_trade_code().isEmpty()) {
            inputString.append(",out_trade_code=" + chrBean.getOut_trade_code());
        }
        if (!chrBean.getAmount().isEmpty()) {
            inputString.append(",amount=" + chrBean.getAmount());
        }
        if (!chrBean.getUser_fee().isEmpty()) {
            inputString.append(",user_fee=" + chrBean.getUser_fee());
        }

        if (!chrBean.getOut_bid_no().isEmpty()) {
            inputString.append(",out_bid_no=" + chrBean.getOut_bid_no());
        }

        if (!chrBean.getGift_money().isEmpty()) {
            inputString.append(",gift_money=" + chrBean.getGift_money());
        }
        if (!chrBean.getSummary().isEmpty()) {
            inputString.append(",summary=" + chrBean.getSummary());
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
        if (ShMgsBaseBean.getSign_type().equals("RSA")) {
            rsa.setPrivateKey(ShMgsBaseBean.getRsakey());
            try {
                sign = rsa.sign(signString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (ShMgsBaseBean.getSign_type().equals("MD5")) {
            String md5SignString = signString + ShMgsBaseBean.getMd5key();
            sign = md5.getMD5(md5SignString, "UTF-8");
        }

        chrBean.setEncodeString(sortString);
        if (!ShMgsBaseBean.getNotify_url().isEmpty()) {
            chrBean.setEncodeString(
                    tool.removeFromString(chrBean.getEncodeString(), "&", "&", "startswith", "notify_url")
                            + "&notify_url=" + tool.textEncode(ShMgsBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!ShMgsBaseBean.getReturn_url().isEmpty()) {
            chrBean.setEncodeString(
                    tool.removeFromString(chrBean.getEncodeString(), "&", "&", "startswith", "return_url")
                            + "&return_url=" + tool.textEncode(ShMgsBaseBean.getReturn_url(), "UTF-8"));
        }

        String requestString = chrBean.getEncodeString().replaceAll("replaceFlag", ",") + "&sign="
                + tool.textEncode(sign, "UTF-8");
        System.out.println("请求报文: " + requestString);

        String responseString = tool.textDncode(tool.post(ShMgsBaseBean.getUrl(), requestString), "UTF-8");
        System.out.println(responseString);

        request.setAttribute("response", responseString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }




}
