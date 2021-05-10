package com.common;

import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public abstract class AesUtil
{

	/**
	 * 加密
	 */
	public static String encrypt(String orignalContent, String key)
	{
		try
		{
			orignalContent = paddStr( JSEscape.escape( orignalContent ), 16, ' ' );
			Cipher cipher = Cipher.getInstance( "AES/ECB/NoPadding" );
			SecretKeySpec skeySpec = new SecretKeySpec( key.getBytes( "UTF-8" ), "AES" );
			cipher.init( Cipher.ENCRYPT_MODE, skeySpec );
			byte[] originalBytes = orignalContent.getBytes( "UTF-8" );
			byte[] encryptedBytes = cipher.doFinal( originalBytes );
			return bytesToHexString( encryptedBytes );
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			throw new RuntimeException( e.getMessage() );
		}
	}

	/**
	 * 解密
	 */
/*	public static String decrypt(String encyptedContent, String key) throws Throwable {
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] encryptedBytes = hexStringToBytes(encyptedContent);
		byte[] original = cipher.doFinal(encryptedBytes);
		String result = new String(original);
		result = StringUtils.trimTrailingCharacter(result, ' ');
		return JSEscape.unescape(result);
	}*/

	/**
	 * @param source  要补位的内容
	 * @param factor  计算因子,补位内容 长度=n*factor
	 * @param paddStr 补位的元素
	 */
	public static String paddStr(String source, int factor, char paddStr)
	{
		if (source == null || "".equals( source.trim() ))
		{
			return source;
		}
		if (source.length() % factor == 0)
		{
			return source;
		}
		int paddLength = factor * (source.length() / factor + 1) - source.length();
		StringBuffer sb = new StringBuffer( source );
		for (int i = 0; i < paddLength; i++)
		{
			sb.append( paddStr );
		}
		return sb.toString();
	}

	private static byte[] hexStringToBytes(String hexString)
	{
		if ((hexString == null) || (hexString.length() < 1))
		{
			return null;
		}
		byte[] result = new byte[hexString.length() / 2];
		for (int i = 0; i < (hexString.length() / 2); i++)
		{
			int high = Integer.parseInt( hexString.substring( i * 2, (i * 2) + 1 ), 16 );
			int low = Integer.parseInt( hexString.substring( (i * 2) + 1, (i * 2) + 2 ), 16 );
			result[i] = (byte) ((high * 16) + low);
		}
		return result;
	}

	public static String bytesToHexString(byte[] src)
	{
		StringBuffer sb = new StringBuffer();
		if ((src == null) || (src.length == 0))
		{
			return null;
		}
		for (byte element : src)
		{
			String hex = Integer.toHexString( element & 0xFF );
			if (hex.length() == 1)
			{
				hex = '0' + hex;
			}
			sb.append( hex );
		}
		return sb.toString();
	}

	public static void main(String[] args)
	{
		String raw = SHAUtil.encryptSha256( "abc" );
		String key = "1100751827014126ae021132fd5395c0";

		System.out.println( encrypt( SHAUtil.encryptSha256( "abc" ), "1100751827014126ae021132fd5395c0" ) );
		System.out.println( encrypt( SHAUtil.encryptSha256( "abc" ), "1100751827014126ae021132fd5395c0" ).equals(
				"8973c4ba44afc815242cb5e722922b3451aa6712fa14dd08a1a3dd0da58e0d4e7772616a183af94f930185122de9f5f26418c2b77db8a8d98846436624068ba4" ) );

	}
}