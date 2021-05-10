package com.controller.wgs;

import com.common.AesUtil;
import com.common.CalculateSign;
import com.common.Tools;
import com.meidusa.fastjson.JSONObject;
import com.wgsBean.PersonalCertifyCheckRealNameBean;
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
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hanxiaodi on 17/11/24.
 */
@Controller
public class PersonalCertifyCheckRealName extends HttpServlet
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "PersonalCertifyCheckRealName")
	public void doPost(@ModelAttribute("wgsathorizedBean") WgsAuthorizedBean wgsathorizedBean,
			@ModelAttribute("pccrBean")PersonalCertifyCheckRealNameBean pccrBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{


		StringBuilder inputString = new StringBuilder("");


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

//		if(!pccrBean.getSalt_id().isEmpty()){
//			inputString.append( ",salt_id="+pccrBean.getSalt_id() );
//		}

		String encryptRealname = AesUtil.encrypt(pccrBean.getName(),pccrBean.getSalt());
		String encryptIcNo = AesUtil.encrypt(pccrBean.getIc_no(),pccrBean.getSalt());

		HashMap<String,String> hashMap = new HashMap<String, String>(  );
		hashMap.put( "name", encryptRealname);
		hashMap.put( "ic_no", encryptIcNo);
		hashMap.put( "salt_id",pccrBean.getSalt_id() );

		 String biz_content = JSONObject.toJSONString( hashMap );

//		inputString.append(",biz_content="+ biz_content );

		Map<String,String> signmap = new TreeMap<String, String>();
		signmap.put( "platform", wgsathorizedBean.getPlatform());
		signmap.put( "version" ,wgsathorizedBean.getVersion());
		signmap.put( "service" ,wgsathorizedBean.getService());
		signmap.put( "access_token" ,wgsathorizedBean.getAccess_token());
		signmap.put( "access_key" ,wgsathorizedBean.getAccess_key());
		signmap.put("biz_content",biz_content);
		System.out.print( CalculateSign.calculateSign( signmap ) );
		String sign = CalculateSign.calculateSign( signmap );




		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
//		String signString = tool.removeFromString(sortString, "&", "&",
//				"startswith", "sign");
//		//sha256加密排序后的请求
//		String sign = SHAUtil.getSha256( signString );






		String requestString = sortString+"&biz_content="+biz_content+"&sign="+sign;
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
