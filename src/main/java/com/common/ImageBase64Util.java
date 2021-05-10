package com.common;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.weihui.gateway.sign.RSA;

/**
 * Created by hanxiaodi on 18/4/2.
 */
public class ImageBase64Util {
	public static void setPublic_key(String public_key)
	{
		ImageBase64Util.public_key = public_key;
	}

	//定义加密的公钥和密钥
	/*测试环境密钥
	* MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB
	* 生产环境密钥
	* MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgzUSRjJijlZudygIjXl9EzqbwQTdnZys88wt4OolaRo6Cwc8RckBupaDdw3Xx3rDzSRHwvO3uMpAYapLpIkVFRq0VmzQG80ZW9pTQ6UBTKx94H+C5jZ1dBudVUun0x6+sW+LZ8vcUY1dRqN62fPN7xSaZXEfgcgu1+pWb5lOYSwIDAQAB
	* */
//	static String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB";
	static String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB";
//   static String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgzUSRjJijlZudygIjXl9EzqbwQTdnZys88wt4OolaRo6Cwc8RckBupaDdw3Xx3rDzSRHwvO3uMpAYapLpIkVFRq0VmzQG80ZW9pTQ6UBTKx94H+C5jZ1dBudVUun0x6+sW+LZ8vcUY1dRqN62fPN7xSaZXEfgcgu1+pWb5lOYSwIDAQAB";
	// /Users/hanxiaodi/Downloads/mianshi.jpg , jgg
	public static void main(String[] args) throws Exception
	{
		String path ="/Users/hanxiaodi/Downloads/h6.jpg";
		imageToBase64(path);
	}

	public static String imageToBase64(String path) throws Exception
	{// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		InputStream in = null;
		try
		{
			in = new FileInputStream( path );
			data = new byte[in.available()];
			data = new byte[in.available()];
			in.read( data );
			in.close();
		}
		catch (IOException e)
		{
			System.out.println( "读取图片异常：" + e );
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					//logger.error("："+e);
					System.out.println( "关闭输入流失败" + e );
				}
			}
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String endocPic = encoder.encode( data );// 返回Base64编码过的字节数组字符串
		String rsaPic = rsaEncrypt( endocPic, "UTF-8" );
		System.out.println( rsaPic );
		return rsaPic;

	}

	public static String rsaEncrypt(String data, String charset) throws Exception{
		return  Base64.encodeBase64String(RSA.encryptByPublicKey(data.getBytes(charset),public_key));
	}


	//新的加密
	public static String imageToBase64New(String path) throws Exception
	{// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		InputStream in = null;
		try
		{
			in = new FileInputStream( path );
			data = new byte[in.available()];
			data = new byte[in.available()];
			in.read( data );
			in.close();
		}
		catch (IOException e)
		{
			System.out.println( "读取图片异常：" + e );
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					//logger.error("："+e);
					System.out.println( "关闭输入流失败" + e );
				}
			}
		}
		// 对字节数组Base64编码
		String all = null;
		BASE64Encoder encoder = new BASE64Encoder();
		String endocPic = encoder.encode(data);// 返回Base64编码过的字节数组字符串
		String fileContentPart1 = endocPic.substring(0,10)+endocPic.substring(endocPic.length()-10,endocPic.length());
		String fileContentPart2 = endocPic.substring(10,endocPic.length()-10);
		String rsaPic = rsaEncrypt( fileContentPart1, "UTF-8" );
		System.out.println("fileContentPart1: \n"+ rsaPic );
		System.out.println("fileContentPart2: \n"+ fileContentPart2 );
		all = rsaPic+"\n fileContentPart2: \n"+ fileContentPart2;
		return all;

	}



}
