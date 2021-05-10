package com.controller;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.mas.bean.MasBaseBean;
import com.consume_pay.bean.CreateSingleConsumeTradeBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by HP on 9/19/2017.
 */
@Controller
public class CreateSingleConsumeTrade extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "CreateSingleConsumeTrade")
    public void doPost(@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
                       @ModelAttribute("csctBean") CreateSingleConsumeTradeBean csctBean,
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
        if (!csctBean.getOut_trade_no().isEmpty()) {
            inputString.append(",out_trade_no=" + csctBean.getOut_trade_no());
        }
        if (!csctBean.getOut_trade_code().isEmpty()) {
            inputString.append(",out_trade_code="
                    + csctBean.getOut_trade_code());
        }
        if (!csctBean.getTrade_close_time().isEmpty()) {
            inputString.append(",trade_close_time="
                    + csctBean.getTrade_close_time());
        }
        if (!csctBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + csctBean.getExtend_param());
        }
        if (!csctBean.getPayer_identity_id().isEmpty()) {
            inputString.append(",payer_identity_id=" + csctBean.getPayer_identity_id());
        }
        if (!csctBean.getPayer_identity_type().isEmpty()) {
            inputString.append(",payer_identity_type="
                    + csctBean.getPayer_identity_type());
        }
        if (!csctBean.getPayer_ip().isEmpty()) {
            inputString.append(",payer_ip=" + csctBean.getPayer_ip());
        }
        String quickPayString = null;
        if (!csctBean.getPay_method().isEmpty()
                && csctBean.getPay_method().startsWith("quick_pay")) {
            Tools tool = new Tools();
            quickPayString = tool.quickPayEncrypt(csctBean.getPay_method(),
                    masBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
            inputString.append(",pay_method=" + quickPayString);
        } else {
            quickPayString = csctBean.getPay_method().replaceAll(",",
                    "replaceFlag");
            inputString.append(",pay_method=" + quickPayString);
        }
        if (!csctBean.getIs_safe_card_support().isEmpty()) {
            inputString.append(",is_safe_card_support="
                    + csctBean.getIs_safe_card_support());
        }
        if (!csctBean.getPayer_fee().isEmpty()) {
            inputString.append(",payer_fee=" + csctBean.getPayer_fee());
        }
        if (!csctBean.getPayee_fee().isEmpty()) {
            inputString.append(",payee_fee=" + csctBean.getPayee_fee());
        }
        if (!csctBean.getPayee_identity_id().isEmpty()) {
            inputString.append(",payee_identity_id="
                    + csctBean.getPayee_identity_id());
        }
        if (!csctBean.getPayee_identity_type().isEmpty()) {
            inputString.append(",payee_identity_type="
                    + csctBean.getPayee_identity_type());
        }
        if (!csctBean.getPayee_account_type().isEmpty()) {
            inputString.append(",payee_account_type="
                    + csctBean.getPayee_account_type());
        }
        if (!csctBean.getOrder_title().isEmpty()) {
            inputString.append(",order_title="
                    + csctBean.getOrder_title());
        }
        if (!csctBean.getOrder_desc().isEmpty()) {
            inputString.append(",order_desc="
                    + csctBean.getOrder_desc());
        }
        if (!csctBean.getGoods_website().isEmpty()) {
            inputString.append(",goods_website="
                    + csctBean.getGoods_website());
        }
        if (!csctBean.getGoods_kind().isEmpty()) {
            inputString.append(",goods_kind="
                    + csctBean.getGoods_kind());
        }
        if (!csctBean.getGoods_type().isEmpty()) {
            inputString.append(",goods_type="
                    + csctBean.getGoods_type());
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

        csctBean.setEncodeString(sortString);
        if (!masBaseBean.getNotify_url().isEmpty()) {
            csctBean.setEncodeString(tool.removeFromString(
                    csctBean.getEncodeString(), "&", "&", "startswith",
                    "notify_url")
                    + "&notify_url="
                    + tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
        }
        if (!masBaseBean.getReturn_url().isEmpty()) {
            csctBean.setEncodeString(tool.removeFromString(
                    csctBean.getEncodeString(), "&", "&", "startswith",
                    "return_url")
                    + "&return_url="
                    + tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
        }
        if (!csctBean.getSummary().isEmpty()) {
            csctBean.setEncodeString(tool.removeFromString(
                    csctBean.getEncodeString(), "&", "&", "startswith",
                    "summary")
                    + "&summary="
                    + tool.textEncode(csctBean.getSummary(), "UTF-8"));
        }
        if (!csctBean.getPay_method().isEmpty()) {
            csctBean.setEncodeString(tool.removeFromString(
                    csctBean.getEncodeString(), "&", "&", "startswith",
                    "pay_method")
                    + "&pay_method="
                    + tool.textEncode(quickPayString, "UTF-8"));
        }

        String requestString = csctBean.getEncodeString().replaceAll(
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
