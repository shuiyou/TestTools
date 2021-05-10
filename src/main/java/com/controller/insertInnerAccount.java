package com.controller;

import com.common.ImageBase64Util;
import com.weihui.dpm.accounting.api.AccountService;
import com.weihui.dpm.accounting.api.enums.CurrencyEnum;
import com.weihui.dpm.accounting.api.requests.InsertInnerAccountReq;
import com.weihui.dpm.accounting.api.responses.InsertInnerAccountResp;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hanxiaodi on 18/11/6.
 */
@Controller
public class insertInnerAccount extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "insertInnerAccount")
	public void encrypt(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String accountName = request.getParameter("accountName");
		String accountTitleNo = request.getParameter("accountTitleNo");
		String curreny = request.getParameter( "curreny" );
		int result_code = insertInneraccount( accountName,accountTitleNo,curreny ).getCode();
		String result_message = insertInneraccount( accountName,accountTitleNo,curreny ).getMessage();
		String resultAccountNo = insertInneraccount( accountName,accountTitleNo,curreny ).getAccountNo();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("accountNo:"+resultAccountNo+"\n code:"+result_code+"\n Message"+result_message);

	}

	private InsertInnerAccountResp insertInneraccount(String accoutName,String actitileNo,String currery){
		InsertInnerAccountResp response;
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass( AccountService.class );
		factory.setAddress( "http://10.65.209.20:8082/dpm-manager/AccountService" );
		AccountService service =(AccountService) factory.create();

		InsertInnerAccountReq req = new InsertInnerAccountReq();
		req.setAccountName( accoutName );
		req.setAccountTitleNo( actitileNo );
		if (currery=="CNY"){
			req.setCurrency( CurrencyEnum.CNY);
		}else {
			req.setCurrency( CurrencyEnum.DOLLOR);
		}
		response =service.insertInnerAccount( req,null );
		System.out.println( "accountNO="+response.getAccountNo()+",message="+response.getMessage() );
		return response;
	}
}

