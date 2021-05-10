package com.toolsImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import com.common.DbEnviornment;
import com.common.MD5Util;
import com.common.Tools;
import com.toolsFacade.GenUserFacade;
import com.weihui.common.domain.OperationEnvironment;
import com.weihui.ma.service.base.response.Response;
import com.weihui.ma.service.facade.IMemberFacade;
import com.weihui.ma.service.facade.IVerifyFacade;
import com.weihui.ma.service.request.MemberIdentityRequest;
import com.weihui.ma.service.request.VerifyIDRequest;
import com.weihui.ma.service.request.VerifyIDResponse;

public class GenUserImpl implements GenUserFacade {

	private static final String UID_FORMAT = "14440000000";
	private static final String IDENTITY_FORMAT = "310107000000000000";
	private String MD5Key = "1234567890qwertyuiopasdfghjklzxc";
	private String MgsUrl = "";
	private String MemberFacadeUrl = "";
	private String VerfiyFacadeUrl = "";

	public String getMgsUrl() {
		return MgsUrl;
	}

	public void setMgsUrl(String mgsUrl) {
		MgsUrl = mgsUrl;
	}

	public String getMemberFacadeUrl() {
		return MemberFacadeUrl;
	}

	public void setMemberFacadeUrl(String memberFacadeUrl) {
		MemberFacadeUrl = memberFacadeUrl;
	}

	public String getVerfiyFacadeUrl() {
		return VerfiyFacadeUrl;
	}

	public void setVerfiyFacadeUrl(String verfiyFacadeUrl) {
		VerfiyFacadeUrl = verfiyFacadeUrl;
	}

	public String CreateUser(int count, String partnerId, String flag)
			throws IOException {
		OpenAccountImpl open = new OpenAccountImpl();
		// open.setCmfUrl("http://10.65.209.11:8091/services/v2/trusteeship");//
		// 测试环境
		open.setCmfUrl("http://10.65.212.17:8581/services/v2/trusteeship");// 压力环境
		StringBuilder errorResult = new StringBuilder("");
		Tools tool = new Tools();
		MD5Util md5util = new MD5Util();
		DecimalFormat df = new DecimalFormat(UID_FORMAT);
		String uid = "";
		for (int i = 1; i <= count; i++) {
			FileWriter fileWriter = new FileWriter(
					"/Users/sunjie/Desktop/Result.txt", true);
			uid = flag + df.format(i);
			// System.out.println("UID::::" + uid);
			StringBuilder request = new StringBuilder(
					"service=create_activate_member,version=1.0,partner_id="
							+ partnerId
							+ ",_input_charset=UTF-8,encrypt_version=1.0,member_type=1,identity_id="
							+ uid + ",identity_type=UID,client_ip=2.2.2.2");
			request.append(",request_time=" + tool.returnSystemDate());

			// MD5加密
			String sign = "";
			String[] splitString = request.toString().split(",");
			String signString = tool.sortStringWithSeparator(splitString, "&");
			String md5SignString = signString + MD5Key;
			sign = md5util.getMD5(md5SignString, "UTF-8");

			// 拼装最终请求参数
			String requestString = null;
			try {
				requestString = signString + "&sign="
						+ tool.textEncode(sign, "UTF-8") + "&sign_type="
						+ "MD5" + "&sign_version=1.0";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 发送请求
			String responseString = null;
			try {
				responseString = tool.textDncode(
						tool.post(getMgsUrl(), requestString), "UTF-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(responseString);
			// 判断结果

			if (responseString.contains("APPLY_SUCCESS")) {
				if (AuthRealName(uid, i).contains("responseMessage=处理成功")) {
					if (AuthMobile(uid, "" + df.format(i)).contains(
							"responseMessage=处理成功")) {
						if (open.OpenAccount(partnerId, getMemberId(uid))
								.contains(
										"instResultCode=S0001,returnMessage=开户成功")) {
							System.out.println(uid + " has been created!");
						} else {
							System.out.println(uid + " open account faild!");
							fileWriter.write(uid + " open account faild"
									+ "\r\n");
							errorResult.append(uid + " open account faild"
									+ "\r\n");
						}
					} else {
						System.out.println(uid + " add mobile failed!");
						fileWriter.write(uid + " add mobile failed" + "\r\n");
						errorResult.append(uid + " add mobile failed" + "\r\n");
					}
				} else {
					System.out.println(uid + " add verification failed");
					fileWriter.write(uid + " add verification failed" + "\r\n");
					errorResult.append(uid + " add verification failed"
							+ "\r\n");
				}
			} else {
				fileWriter.write(uid + " hasn't been created! response is::::"
						+ responseString + "\r\n");
				System.out.println(uid
						+ " hasn't been created! response is::::"
						+ responseString);
				errorResult.append(uid + " create failed" + "\r\n");
			}
			fileWriter.close();
		}
		return errorResult.toString();
	}

	public String AuthRealName(String uid, int loopNum) {
		DecimalFormat idDf = new DecimalFormat(IDENTITY_FORMAT);
		VerifyIDRequest verifyIDRequest = new VerifyIDRequest();
		OperationEnvironment operationEnvironment = new OperationEnvironment();
		verifyIDRequest.setFlag(false);
		verifyIDRequest.setMemberId(getMemberId("" + uid));
		// verifyIDRequest.setPid("69");// 测试环境1019
		verifyIDRequest.setPid("4");// 压力环境
		verifyIDRequest.setVerifyEntity(idDf.format(loopNum));
		verifyIDRequest.setVerifyName("马云飞");
		verifyIDRequest.setVerifyType(1);

		VerifyIDResponse response = new VerifyIDResponse();
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(IVerifyFacade.class);
		factory.setAddress(getVerfiyFacadeUrl());
		IVerifyFacade service = (IVerifyFacade) factory.create();
		try {
			response = service.verifyId(operationEnvironment, verifyIDRequest);
		} catch (Exception e) {
			System.out.println("GET RESPONSE ERROR: " + e.getMessage());
		}
		System.out.println("" + response);
		return "" + response;
	}

	public String AuthMobile(String uid, String mobile) {
		MemberIdentityRequest memberIdentityRequest = new MemberIdentityRequest();
		OperationEnvironment operationEnvironment = new OperationEnvironment();
		memberIdentityRequest.setIdentityType(2);
		memberIdentityRequest.setMemberId(getMemberId(uid));
		memberIdentityRequest.setMemberIdentity(mobile);
		// memberIdentityRequest.setPlatformType("69");// 测试环境
		memberIdentityRequest.setPlatformType("4");// 压力环境

		Response response = new Response();
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(IMemberFacade.class);
		factory.setAddress(getMemberFacadeUrl());
		IMemberFacade service = (IMemberFacade) factory.create();
		try {
			response = service.addMemberIdentity(operationEnvironment,
					memberIdentityRequest);
		} catch (Exception e) {
			System.out.println("GET RESPONSE ERROR: " + e.getMessage());
		}
		 System.out.println("" + response);
		return "" + response;
	}

	private String getMemberId(String uid) {
		String memberId = "";
		try {
			DbEnviornment env = new DbEnviornment();
			// env.Connect("10.65.193.11:1521", "whpay", "reader",
			// "tF1P7IC7oKa6ua");// 测试环境
			env.Connect("10.65.212.193:1521", "orayali", "qa", "qa");// 压力环境
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

	public static void main(String[] args) throws IOException {
		GenUserImpl test = new GenUserImpl();
		test.setMgsUrl("http://10.65.209.24:8083/mgs/gateway.do");
		test.setMemberFacadeUrl("http://10.65.209.11:8081/ma-web/MemberFacade");
		test.setVerfiyFacadeUrl("http://10.65.209.11:8081/ma-web/VerifyFacade");
		// test.setMgsUrl("http://yaligate.pay.sina.com.cn/mgs/gateway.do");
		// test.setMemberFacadeUrl("http://10.65.212.15:8025/ma-web/MemberFacade");
		// test.setVerfiyFacadeUrl("http://10.65.212.15:8025/ma-web/VerifyFacade");
		test.CreateUser(1, "200003670082", "test");
	}

}
