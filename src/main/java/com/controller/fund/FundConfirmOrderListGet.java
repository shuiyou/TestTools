package com.controller.fund;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.fund.bean.FundBaseBean;
import com.fund.bean.FundConfirmOrderListGetBean;
import com.fund.bean.FundTradeRedeemOrderAddBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/13.
 */

@Controller
public class FundConfirmOrderListGet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "FundConfirmOrderListGet")
    public void doPost(@ModelAttribute("FundBaseBean") FundBaseBean fundBaseBean,
                       @ModelAttribute("fcolgBean") FundConfirmOrderListGetBean fcolgBean,
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
        if (!fundBaseBean.getStart_time().isEmpty()) {
            inputString.append(",start_time=" + fundBaseBean.getStart_time());
        }
        if (!fundBaseBean.getEnd_time().isEmpty()) {
            inputString.append(",end_time=" + fundBaseBean.getEnd_time());
        }
        if (!fundBaseBean.getPage_no().isEmpty()) {
            inputString.append(",page_no=" + fundBaseBean.getPage_no());
        }
        if (!fundBaseBean.getPage_size().isEmpty()) {
            inputString.append(",page_size=" + fundBaseBean.getPage_size());
        }
        if (!fcolgBean.getMember_id().isEmpty()) {
            inputString.append(",member_id=" + fcolgBean.getMember_id());
        }
        if (!fcolgBean.getTrade_order_no().isEmpty()) {
            inputString.append(",trade_order_no=" + fcolgBean.getTrade_order_no());
        }
        if (!fcolgBean.getFund_code_list().isEmpty()) {
            inputString.append(",fund_code_list=" + fcolgBean.getFund_code_list());
        }
        if (!fcolgBean.getFilter_fund_code_list().isEmpty()) {
            inputString.append(",filter_fund_code_list=" + fcolgBean.getFilter_fund_code_list());
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

        fcolgBean.setEncodeString(sortString);
        String requestString = fcolgBean.getEncodeString() + "&sign="
                + tool.textEncode(sign, "UTF-8");
        System.out.println("????????????: " + requestString);

        String responseString = tool.post(fundBaseBean.getUrl(), requestString);
        String dataString = tool.base64Decode(responseString.substring(responseString.indexOf("data=") + 5, responseString.indexOf("&sign")));
        System.out.println(responseString);

        request.setAttribute("response", dataString);
        request.setAttribute("request", requestString);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}
