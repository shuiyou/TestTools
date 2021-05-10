package com.controller.sh_bank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh_bank.bean.CancelBidInfo_shBean;
import com.sh_bank.bean.DebtAssignApply_shBean;
import com.sh_bank.bean.ShMasBaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.mas.bean.CancelBidBean;
import com.mas.bean.MasBaseBean;

/**
 *
 * @author linshunxiang
 *
 */
@Controller
public class DebtAssignApply_sh extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "DebtAssignApply_sh")
    public void doPost(@ModelAttribute("masBaseBean") ShMasBaseBean masBaseBean,
                       @ModelAttribute("cbiBean") DebtAssignApply_shBean cbiBean,
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
        if (!masBaseBean.getCashdesk_addr_category().isEmpty()) {
            inputString
                    .append(",cashdesk_addr_category=" + masBaseBean.getCashdesk_addr_category());
        }
        if (!masBaseBean.getReturn_url().isEmpty()) {
            inputString.append(",return_url=" + masBaseBean.getReturn_url());
        }
        if (!masBaseBean.getMemo().isEmpty()) {
            inputString.append(",memo=" + masBaseBean.getMemo());
        }
        if (!cbiBean.getOut_trade_no().isEmpty()) {
            inputString.append(",out_trade_no=" + cbiBean.getOut_trade_no());
        }
        if (!cbiBean.getPayer_identity_id().isEmpty()) {
            inputString.append(",payer_identity_id=" + cbiBean.getPayer_identity_id());
        }
        if (!cbiBean.getPayer_identity_type().isEmpty()) {
            inputString.append(",payer_identity_type=" + cbiBean.getPayer_identity_type());
        }
        if (!cbiBean.getPayer_account_type().isEmpty()) {
            inputString.append(",payer_account_type=" + cbiBean.getPayer_account_type());
        }

        if (!cbiBean.getPayee_identity_id().isEmpty()) {
            inputString.append(",payee_identity_id=" + cbiBean.getPayee_identity_id());
        }
        if (!cbiBean.getPayee_identity_type().isEmpty()) {
            inputString.append(",payee_identity_type=" + cbiBean.getPayee_identity_type());
        }
        if (!cbiBean.getPayee_account_type().isEmpty()) {
            inputString.append(",payee_account_type=" + cbiBean.getPayee_account_type());
        }

        if (!cbiBean.getAmount().isEmpty()) {
            inputString.append(",amount=" + cbiBean.getAmount());
        }
        if (!cbiBean.getOut_bid_no().isEmpty()) {
            inputString.append(",out_bid_no=" + cbiBean.getOut_bid_no());
        }
        if (!cbiBean.getTr_fee().isEmpty()) {
            inputString.append(",tr_fee=" + cbiBean.getTr_fee());
        }

        if (!cbiBean.getGift_money().isEmpty()) {
            inputString.append(",gift_money=" + cbiBean.getGift_money());
        }

        if (!cbiBean.getTr_share().isEmpty()) {
            inputString.append(",tr_share=" + cbiBean.getTr_share());
        }
        if (!cbiBean.getTr_yield().isEmpty()) {
            inputString.append(",tr_yield=" + cbiBean.getTr_yield());
        }
        if (!cbiBean.getTr_int_date().isEmpty()) {
            inputString.append(",tr_int_date=" + cbiBean.getTr_int_date());
        }
        if (!cbiBean.getSummary().isEmpty()) {
            inputString.append(",summary=" + cbiBean.getSummary());
        }
        if (!cbiBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + cbiBean.getExtend_param());
        }

        if (!cbiBean.getOut_trade_code().isEmpty()) {
            inputString.append(",out_trade_code=" + cbiBean.getOut_trade_code());
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

        cbiBean.setEncodeString(sortString);
        if (!masBaseBean.getNotify_url().isEmpty()) {
            cbiBean.setEncodeString(tool.removeFromString(
                    cbiBean.getEncodeString(), "&", "&", "startswith",
                    "notify_url")
                    + "&notify_url="
                    + tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!masBaseBean.getReturn_url().isEmpty()) {
            cbiBean.setEncodeString(tool.removeFromString(
                    cbiBean.getEncodeString(), "&", "&", "startswith",
                    "return_url")
                    + "&return_url="
                    + tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
        }
        if (!cbiBean.getSummary().isEmpty()) {
            cbiBean.setEncodeString(tool.removeFromString(
                    cbiBean.getEncodeString(), "&", "&", "startswith",
                    "summary")
                    + "&summary="
                    + tool.textEncode(cbiBean.getSummary(), "UTF-8"));
        }


        String requestString = cbiBean.getEncodeString().replaceAll(
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
