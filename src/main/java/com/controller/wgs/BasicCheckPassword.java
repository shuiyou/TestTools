package com.controller.wgs;

import com.common.AesUtil;
import com.common.SHAUtil;
import com.common.Tools;
import com.meidusa.fastjson.JSONObject;
import com.wgsBean.BasicCheckPasswordBean;
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
 * Created by hanxiaodi on 17/12/8.
 */
@Controller
public class BasicCheckPassword extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "BasicCheckPassword")
	public void doPost(@ModelAttribute("wgsathorizedBean") WgsAuthorizedBean wgsathorizedBean,
			@ModelAttribute("bcpBean")BasicCheckPasswordBean bcpBean,
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
		if(!wgsathorizedBean.getAccess_token().isEmpty()){
			inputString.append(",access_token=" + wgsathorizedBean.getAccess_token());
		}

		if (!wgsathorizedBean.getAccess_key().isEmpty()) {
			inputString.append(",access_key=" + wgsathorizedBean.getAccess_key());
		}

		String encryptPassword = AesUtil.encrypt( bcpBean.getPassword(),bcpBean.getSalt() );
		HashMap<String,String> hashMap = new HashMap<String, String>(  );
		hashMap.put( "salt_id",bcpBean.getSalt_id());
		hashMap.put( "password",encryptPassword);
		String need = JSONObject.toJSONString( hashMap );

		//		if(!bgvcBean.getScene().isEmpty()&&!bgvcBean.getSend_type().isEmpty()){
		//			inputString.append( ",biz_content="+need);
		//		}

		inputString.append( ",biz_content="+ JSONObject.toJSONString( hashMap ).replace( ",","replaceFlag" ));








		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&").replace( "replaceFlag","," );
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign");
		String sign = SHAUtil.getSha256( signString );


		//	String checkVerifyString = JSONObject.toJSONString( hashMap );






		String requestString = sortString+"&sign="+sign;
		System.out.println("请求报文: " + requestString);

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
