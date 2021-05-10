package com.toolsImpl;

import java.sql.ResultSet;
import java.util.UUID;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.common.DbEnviornment;
import com.toolsFacade.OpenAccFacade;
import com.weihui.cmf.service.facade.api.trusteeship.TrusteeshipFacade;
import com.weihui.cmf.service.facade.domain.trusteeship.TrusteeshipOpenAccountRequest;
import com.weihui.cmf.service.facade.domain.trusteeship.TrusteeshipOpenAccountResponse;
import com.weihui.common.domain.OperationEnvironment;
import com.weihui.payment.common.v2.enums.PayMode;

public class OpenAccountImpl implements OpenAccFacade {

	private String cmfUrl = "";

	public String getCmfUrl() {
		return cmfUrl;
	}

	public void setCmfUrl(String cmfUrl) {
		this.cmfUrl = cmfUrl;
	}

	public String OpenAccount(String pid, String memberId) {
		TrusteeshipOpenAccountRequest trusteeshipOpenAccountRequest = new TrusteeshipOpenAccountRequest();
		OperationEnvironment operationEnvironment = new OperationEnvironment();
		trusteeshipOpenAccountRequest.setPid(pid);
		trusteeshipOpenAccountRequest.setMemberId(memberId);
		trusteeshipOpenAccountRequest.setRequestNo(UUID.randomUUID().toString()
				.replace("-", "").substring(0, 31));
		trusteeshipOpenAccountRequest.setInstCode("BANK");
		trusteeshipOpenAccountRequest.setPayMode(PayMode.SAVING_POT);

		TrusteeshipOpenAccountResponse response = new TrusteeshipOpenAccountResponse();
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(TrusteeshipFacade.class);
		factory.setAddress(getCmfUrl());
		TrusteeshipFacade service = (TrusteeshipFacade) factory.create();
		try {
			response = service.openAccount(trusteeshipOpenAccountRequest,
					operationEnvironment);
		} catch (Exception e) {
			System.out.println("GET RESPONSE ERROR: " + e.getMessage());
		}
		System.out.println("" + response);
		return "" + response;
	}

	public String getMemberId(String uid) {
		String memberId = "";
		try {
			DbEnviornment env = new DbEnviornment();
			 env.Connect("10.65.193.11:1521", "whpay", "reader",
			 "tF1P7IC7oKa6ua");
//			env.Connect("10.65.212.193:1521", "orayali", "qa", "qa");
			String sql = "select * from member.tm_member_identity where identity = '"
					+ uid + "'";
			ResultSet queryResult = DbEnviornment.statement.executeQuery(sql);
			if (queryResult.next()) {
				memberId = queryResult.getString("member_id");
			}
			queryResult.close();
			com.common.DbEnviornment.con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return memberId;
	}

}
