package com.controller.wgs;

import com.common.AesUtil;
import com.common.SHAUtil;
import com.common.Tools;
import com.meidusa.fastjson.JSONObject;
import com.wgsBean.PersonalBankCardBindingBean;
import com.wgsBean.WgsAuthorizedBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/29.
 */
@Controller
public class PersonalBankCardBinding  extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "PersonalBankCardBinding")
    public void doPost(@ModelAttribute("wgsathorizedBean") WgsAuthorizedBean wgsathorizedBean,
                       @ModelAttribute("pbcBean") PersonalBankCardBindingBean pbcBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {


        StringBuilder inputString = new StringBuilder("");
        StringBuilder biz_content = new StringBuilder("");

        if (!wgsathorizedBean.getPlatform().isEmpty()) {
            inputString.append("platform=" + wgsathorizedBean.getPlatform());
        }
        if (!wgsathorizedBean.getVersion().isEmpty()) {
            inputString.append(",version=" + wgsathorizedBean.getVersion());
        }
        if (!wgsathorizedBean.getService().isEmpty()) {
            inputString.append(",service=" + wgsathorizedBean.getService());
        }
        if (!wgsathorizedBean.getLang().isEmpty()) {
            inputString.append(",lang=" + wgsathorizedBean.getLang());
        }

//        if (!wgsBean.getSalt_id().isEmpty()) {
//            inputString.append(",salt_id=" + wgsBean.getSalt_id());
//        }
//        if (!pbcBean.getSalt().isEmpty()) {
//            inputString.append(",salt=" + pbcBean.getSalt());
//        }
        if (!wgsathorizedBean.getAccess_token().isEmpty()) {
            inputString.append(",access_token=" + wgsathorizedBean.getAccess_token());
        }
        if (!wgsathorizedBean.getAccess_key().isEmpty()) {
            inputString.append(",access_key=" + wgsathorizedBean.getAccess_key());
        }


        String encryptCard_no = AesUtil.encrypt(pbcBean.getCard_no(),pbcBean.getSalt());
        String encryptCard_mobile = AesUtil.encrypt(pbcBean.getCard_mobile(),pbcBean.getSalt());
        String encryptValid_period = AesUtil.encrypt(pbcBean.getValid_period(),pbcBean.getSalt());
        String encryptSafe_code = AesUtil.encrypt(pbcBean.getSafe_code(),pbcBean.getSalt());

        HashMap<String,String> hashMap = new HashMap<String, String>(  );
        hashMap.put( "card_no", encryptCard_no);
        hashMap.put( "card_mobile", encryptCard_mobile);
        hashMap.put( "valid_period", encryptValid_period);
        hashMap.put( "safe_code", encryptSafe_code);
        hashMap.put( "card_type",pbcBean.getCard_type() );
        hashMap.put( "bank_code",pbcBean.getBank_code() );
        hashMap.put( "salt_id",pbcBean.getSalt_id() );

        inputString.append(",biz_content=" + JSONObject.toJSONString( hashMap ).replace(",", "replaceFlag"));







        String[] splitString = inputString.toString().split(",");
        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");

        String signString =tool.removeFromString(sortString, "&", "&",
                "startswith", "sign");
        String sign = SHAUtil.getSha256( signString.replace("replaceFlag",",") );

        String requestString = sortString.replace("replaceFlag", ",")+"&sign="+sign;



        String responseString = tool.textDncode(
                tool.post(wgsathorizedBean.getUrl(), requestString), "UTF-8");
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
