package com.toolsTest;

import java.io.IOException;

import com.toolsImpl.GenUserImpl;

public class Test {

	public static void main(String[] args) throws IOException {
		GenUserImpl test = new GenUserImpl();
		test.setMgsUrl("http://yaligate.pay.sina.com.cn/mgs/gateway.do");
		test.setMemberFacadeUrl("http://10.65.212.15:8095/ma-web/MemberFacade");
		test.setVerfiyFacadeUrl("http://10.65.212.15:8095/ma-web/VerifyFacade");
//		test.setMgsUrl("http://10.65.209.24:8083/mgs/gateway.do");
//		test.setMemberFacadeUrl("http://10.65.209.11:8081/ma-web/MemberFacade");
//		test.setVerfiyFacadeUrl("http://10.65.209.11:8081/ma-web/VerifyFacade");
		String result = test.CreateUser(1, "200003670082", "bankacc");
		System.out.println(result);
	}

}
