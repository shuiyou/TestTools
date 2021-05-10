package com.toolsTest;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import com.toolsImpl.OpenAccountImpl;

public class OpenAccTest {

	private static final String UID_FORMAT = "58700000000";

	public static void main(String[] args) throws IOException {
		OpenAccountImpl test = new OpenAccountImpl();
		//http://10.65.212.17:8581/services/v2/trusteeship
		//http://10.65.209.11:8091/services/v2/trusteeship
		test.setCmfUrl("http://10.65.212.17:8581/services/v2/trusteeship");
		for (int i = 1; i <= 1; i++) {
			FileWriter fileWriter = new FileWriter(
					"/Users/sunjie/Desktop/Result.txt", true);
			DecimalFormat df = new DecimalFormat(UID_FORMAT);
			String uid = "bankacc" + df.format(i);
			if (test.OpenAccount("200005450248", test.getMemberId(uid))
					.contains("returnMessage=交易成功")) {
				System.out.println(uid + " open account success!");
			} else {
				System.out.println(uid + " open account failed!");
				fileWriter.write(uid + " open account failed!" + "\r\n");
			}
			fileWriter.close();
		}
	}

}
