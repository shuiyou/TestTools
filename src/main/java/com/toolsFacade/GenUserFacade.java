package com.toolsFacade;

import java.io.IOException;

public interface GenUserFacade {
	
	String CreateUser(int count, String partnerId, String flag) throws IOException;

	String AuthRealName(String uid, int loopNum);

	String AuthMobile(String uid, String mobile);

}
