package com.controller.wgs;
import com.common.SHAUtil;
import com.common.Tools;
import com.meidusa.fastjson.JSONObject;
import com.wgsBean.PersonalBankCardBindingAdBean;
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
 * Created by Administrator on 2017/11/30.
 */
@Controller
public class PersonalBankCardBindingAd extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "PersonalBankCardBindingAd")
    public void doPost(@ModelAttribute("wgsAuthorizedBean") WgsAuthorizedBean wgsAuthorizedBean,
                       @ModelAttribute("pbbaBean") PersonalBankCardBindingAdBean pbbaBean,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {

        StringBuilder inputString = new StringBuilder("");

        if (!wgsAuthorizedBean.getPlatform().isEmpty()) {
            inputString.append("platform=" + wgsAuthorizedBean.getPlatform());
        }
        if (!wgsAuthorizedBean.getVersion().isEmpty()) {
            inputString.append(",version=" + wgsAuthorizedBean.getVersion());
        }
        if (!wgsAuthorizedBean.getService().isEmpty()) {
            inputString.append(",service=" + wgsAuthorizedBean.getService());
        }
        if (!wgsAuthorizedBean.getLang().isEmpty()) {
            inputString.append(",lang=" + wgsAuthorizedBean.getLang());
        }
        if (!wgsAuthorizedBean.getAccess_token().isEmpty()) {
            inputString.append(",access_token=" + wgsAuthorizedBean.getAccess_token());
        }
        if (!wgsAuthorizedBean.getAccess_key().isEmpty()) {
            inputString.append(",access_key=" + wgsAuthorizedBean.getAccess_key());
        }


        HashMap<String,String> hashMap = new HashMap<String, String>(  );

        hashMap.put( "verify_token",pbbaBean.getVerify_token() );
        hashMap.put( "valid_code",pbbaBean.getValid_code() );

        inputString.append(",biz_content=" + JSONObject.toJSONString( hashMap ).replace(",", "replaceFlag"));







        String[] splitString = inputString.toString().split(",");
        Tools tool = new Tools();
        String sortString = tool.sortStringWithSeparator(splitString, "&");

        String signString =tool.removeFromString(sortString, "&", "&",
                "startswith", "sign");
        String sign = SHAUtil.getSha256( signString.replace("replaceFlag",",") );

        String requestString = sortString.replace("replaceFlag", ",")+"&sign="+sign;



        String responseString = tool.textDncode(
                tool.post(wgsAuthorizedBean.getUrl(), requestString), "UTF-8");
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
