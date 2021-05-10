package com.common;



import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hanxiaodi on 17/12/5.
 */
public class CalculateSign
{
	public  static String calculateSign(Map<String, String> params) {
		Map<String, String> sorted = new TreeMap<String, String>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sorted.put(entry.getKey(), entry.getValue());
		}
//		sorted.put("access_key", accessKey);
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> e : sorted.entrySet()) {
			if (e.getValue() == null) {
				continue;
			}
			sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
		}
		sb.setLength(sb.length() - 1);
		String plain = sb.toString();
		System.out.println("[UNIT-TEST] 签名原文: " + plain);
		return SHAUtil.getSha256(plain);
	}

}
