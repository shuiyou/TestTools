package com.controller;

import com.meidusa.venus.annotations.Param;
import com.weihui.common.domain.OperationEnvironment;
import com.weihui.ma.service.base.response.Response;
import com.weihui.ma.service.facade.IMemcachedFacade;
import com.weihui.ma.service.facade.IPayPwdFacade;
import com.weihui.ma.service.request.*;
import com.weihui.ma.service.response.PayPwdCheckResponse;
import com.weihui.ma.service.response.PayPwdDecryptResponse;
import com.weihui.ma.service.response.ValidatePayPwdResponse;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by hanxiaodi on 18/11/21.
 */
public class setMerchantPassword
{

	private void setMerchantPassword() {
		OperationEnvironment en = new OperationEnvironment();
		PayPasswordRequest request = new PayPasswordRequest();
		request.setAccountId( "aaa" );
		request.setOperatorId( "124" );
		request.setPaypwd( "435" );
		request.setSalt( "" );
		request.setOrgCode( "CFCA" );
		request.setShaEncryptFlag( "Y" );

//		String response = payPwdFacade.setPayPasswordV1( en,request ).toString();

	}

	public static void main (String[] args) {
		setMerchantPassword p = new setMerchantPassword();
		p.setMerchantPassword();
	}
}
