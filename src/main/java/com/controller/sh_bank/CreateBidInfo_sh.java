package com.controller.sh_bank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh_bank.bean.CreateBidInfo_shBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.mas.bean.MasBaseBean;

/**
 *
 * @author linshunxiang
 *
 */
@Controller
public class CreateBidInfo_sh extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "CreateBidInfo_sh")
    public void doPost(@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
                       @ModelAttribute("cbiBean") CreateBidInfo_shBean cbiBean,
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
        if (!masBaseBean.getCashdesk_addr_category().isEmpty()) {
            inputString.append(",cashdesk_addr_category=" + masBaseBean.getCashdesk_addr_category());
        }
        if (!cbiBean.getOut_bid_no().isEmpty()) {
            inputString.append(",out_bid_no=" + cbiBean.getOut_bid_no());
        }
        if (!cbiBean.getSummary().isEmpty()) {
            inputString.append(",summary=" + cbiBean.getSummary());
        }
        if (!cbiBean.getWeb_site_name().isEmpty()) {
            inputString.append(",web_site_name=" + cbiBean.getWeb_site_name());
        }
        if (!cbiBean.getBid_name().isEmpty()) {
            inputString.append(",bid_name=" + cbiBean.getBid_name());
        }
        if (!cbiBean.getBid_type().isEmpty()) {
            inputString.append(",bid_type=" + cbiBean.getBid_type());
        }
        if (!cbiBean.getBid_amount().isEmpty()) {
            inputString.append(",bid_amount=" + cbiBean.getBid_amount());
        }
        if (!cbiBean.getBid_year_rate().isEmpty()) {
            inputString.append(",bid_year_rate=" + cbiBean.getBid_year_rate());
        }
        if (!cbiBean.getBid_duration().isEmpty()) {
            inputString.append(",bid_duration=" + cbiBean.getBid_duration());
        }
        if (!cbiBean.getRepay_type().isEmpty()) {
            inputString.append(",repay_type=" + cbiBean.getRepay_type());
        }
        if (!cbiBean.getProtocol_type().isEmpty()) {
            inputString.append(",protocol_type=" + cbiBean.getProtocol_type());
        }
        if (!cbiBean.getBid_product_type().isEmpty()) {
            inputString.append(",bid_product_type=" + cbiBean.getBid_product_type());
        }
        if (!cbiBean.getRecommend_inst().isEmpty()) {
            inputString.append(",recommend_inst=" + cbiBean.getRecommend_inst());
        }
        if (!cbiBean.getLimit_min_bid_copys().isEmpty()) {
            inputString.append(",limit_min_bid_copys=" + cbiBean.getLimit_min_bid_copys());
        }
        if (!cbiBean.getLimit_per_copy_amount().isEmpty()) {
            inputString.append(",limit_per_copy_amount=" + cbiBean.getLimit_per_copy_amount());
        }
        if (!cbiBean.getLimit_max_bid_amount().isEmpty()) {
            inputString.append(",limit_max_bid_amount=" + cbiBean.getLimit_max_bid_amount());
        }
        if (!cbiBean.getLimit_min_bid_amount().isEmpty()) {
            inputString.append(",limit_min_bid_amount=" + cbiBean.getLimit_min_bid_amount());
        }
        if (!cbiBean.getUrl2().isEmpty()) {
            inputString.append(",url=" + cbiBean.getUrl2());
        }
        if (!cbiBean.getBegin_date().isEmpty()) {
            inputString.append(",begin_date=" + cbiBean.getBegin_date());
        }
        if (!cbiBean.getTerm().isEmpty()) {
            inputString.append(",term=" + cbiBean.getTerm());
        }
        if (!cbiBean.getGuarantee_method().isEmpty()) {
            inputString.append(",guarantee_method=" + cbiBean.getGuarantee_method());
        }
        if (!cbiBean.getBorrower_info_list().isEmpty()) {
            inputString.append(",borrower_info_list=" + cbiBean.getBorrower_info_list());
        }
        if (!cbiBean.getInt_pay_day().isEmpty()) {
            inputString.append(",int_pay_day=" + cbiBean.getInt_pay_day());
        }if (!cbiBean.getInt_pay_type().isEmpty()) {
            inputString.append(",int_pay_type=" + cbiBean.getInt_pay_type());
        }
        if (!cbiBean.getExtend_param().isEmpty()) {
            inputString.append(",extend_param=" + cbiBean.getExtend_param());
        }

        if (!cbiBean.getRaise_begin_date().isEmpty()) {
            inputString.append(",raise_begin_date=" + cbiBean.getRaise_begin_date());
        }
        if (!cbiBean.getRaise_end_date().isEmpty()) {
            inputString.append(",raise_end_date=" + cbiBean.getRaise_end_date());
        }
        if (!cbiBean.getAssure_account_identity().isEmpty()) {
            inputString.append(",assure_account_identity=" + cbiBean.getAssure_account_identity());
        }
        if (!cbiBean.getDealer_account_identity().isEmpty()) {
            inputString.append(",dealer_account_identity=" + cbiBean.getDealer_account_identity());
        }
        if (!cbiBean.getRepay_account_identity().isEmpty()) {
            inputString.append(",repay_account_identity=" + cbiBean.getRepay_account_identity());
        }
        if (!cbiBean.getAssure_identity_id().isEmpty()) {
            inputString.append(",assure_identity_id=" + cbiBean.getAssure_identity_id());
        }
        if (!cbiBean.getAssure_identity_type().isEmpty()) {
            inputString.append(",assure_identity_type=" + cbiBean.getAssure_identity_type());
        }
        if (!cbiBean.getDealer_identity_id().isEmpty()) {
            inputString.append(",dealer_identity_id=" + cbiBean.getDealer_identity_id());
        }
        if (!cbiBean.getDealer_identity_type().isEmpty()) {
            inputString.append(",dealer_identity_type=" + cbiBean.getDealer_identity_type());
        }
        if (!cbiBean.getRepay_identity_id().isEmpty()) {
            inputString.append(",repay_identity_id=" + cbiBean.getRepay_identity_id());
        }
        if (!cbiBean.getRepay_identity_type().isEmpty()) {
            inputString.append(",repay_identity_type=" + cbiBean.getRepay_identity_type());
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
