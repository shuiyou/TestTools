package com.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author sunjie
 *
 */
public class MD5Util {

	public static void main(String[] args) {
		MD5Util test = new MD5Util();
//		String content = "$signString1234567890qwertyuiopasdfghjklzxc";
		String content ="adfjsdjfsdjfsdjfklsdjflsjdflsdlf";
		System.out.println(content);
		System.out.println(test.getMD5(content, "UTF-8"));
	}

	/**
	 * MD5加密content
	 * 
	 * @param signResource
	 * @param key
	 * @return
	 */
	public String assembleMD5Resource(String signResource, String key) {
		StringBuilder signResourceCode = new StringBuilder();
		// RSAUtil.removeNull移除值为null和空
		signResourceCode.append(RSAUtil.removeNull(signResource));
		signResourceCode.append("&key=" + key);
		return signResourceCode.toString();
	}

	/**
	 * MD5码加密
	 *
	 * @param str
	 *            所需加密字段
	 * @param encode
	 *            编码格式：UTF-8
	 * @return
	 */
	public static String removeNull(String value) {
		value = value + "&";
		StringBuffer valueBuffer = new StringBuffer();
		int startIndex = 0;
		int tempIndex, equalIndex;
		while (true) {
			equalIndex = value.indexOf("=", startIndex + 1);
			tempIndex = value.indexOf("&", startIndex + 1);
			if (equalIndex + 1 != tempIndex && tempIndex > 0) {
				if (!value.substring(equalIndex + 1, tempIndex).toUpperCase()
						.equals("NULL")) {
					valueBuffer.append(value.substring(startIndex, tempIndex));
					valueBuffer.append("&");
				}
			}

			if (tempIndex == value.length() - 1) {
				valueBuffer.deleteCharAt(valueBuffer.length() - 1);
				break;
			}
			startIndex = tempIndex + 1;
		}
		return valueBuffer.toString();
	}

	public String getMD5(String str, String encode) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes(encode));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++)
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		return md5StrBuff.toString();
	}

	public String qrMD5(String plainText, String key) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update((plainText + key).getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();
			return re_md5;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
