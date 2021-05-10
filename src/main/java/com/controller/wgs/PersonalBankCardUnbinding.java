package com.controller.wgs;

import com.common.SHAUtil;
import com.common.Tools;
import com.meidusa.fastjson.JSONObject;
import com.wgsBean.PersonalBankCardUnbindingBean;
import com.wgsBean.WgsAuthorizedBean;
import com.wgsBean.WgsBean;
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
 * Created by Administrator on 2017/12/1.
 */
@Controller
public class PersonalBankCardUnbinding extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "PersonalBankCardUnbinding")
    public void doPost(@ModelAttribute("wgsathorizedBean") WgsAuthorizedBean wgsathorizedBean,
                       @ModelAttribute("rgsBean") PersonalBankCardUnbindingBean wgsBean,
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
        if (!wgsBean.getCard_id().isEmpty()) {
            inputString.append(",card_id=" + wgsBean.getCard_id());
        }
        if (!wgsathorizedBean.getAccess_token().isEmpty()) {
            inputString.append(",access_token=" + wgsathorizedBean.getAccess_token());
        }
        if (!wgsathorizedBean.getAccess_key().isEmpty()) {
            inputString.append(",access_key=" + wgsathorizedBean.getAccess_key());
        }


        HashMap<String,String> hashMap = new HashMap<String, String>(  );
        hashMap.put( "card_id", wgsBean.getCard_id());
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
