package com.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by hanxiaodi on 17/11/16.
 */
public class SHAUtil
{
	public static String getSha256(String plain) {
		return encryptSha256(plain);
	}

	public static String encryptSha256(String plain) {
		//        Digest.encrypt("SHA-256", src,"UTF-8");

		MessageDigest messageDigest;
		String result;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(plain.getBytes("UTF-8"));
			result = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}catch (UnsupportedEncodingException e){
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 将byte转为16进制
	 */
	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				//1得到一位的进行补0操作
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}

	public static void main(String[] args) {
		System.out.println(getSha256("abc")
				.equals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"));
	}
}
