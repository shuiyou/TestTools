package com.common;

import com.weihui.ues.UesClient;
import com.weihui.ues.crypto.model.EncryptType;
import com.weihui.ues.ctx.EncryptContext;
import com.weihui.ues.ctx.params.EchoSummary;
import com.weihui.ues.ctx.params.EncryptParameter;
import com.weihui.ues.model.EncryptData;
import com.weihui.ues.model.UesResult;
import com.weihui.ues.services.UesRemoteService;
import org.apache.commons.lang.Validate;

import java.lang.reflect.InvocationTargetException;
import java.security.cert.X509Certificate;

import static com.weihui.ues.crypto.model.EncryptType.RSA;

/**
 * Created by hanxiaodi on 18/7/6.
 */
public class test
{
	//创建实例对象
	private UesClient useclient = new UesClient();
	private UesRemoteService uesRemoteService;
	private EncryptParameter encryptParameter = new EncryptParameter();
	private EncryptData encryptData  = new EncryptData();

	public static void main(String args[])throws Exception{
		X509Certificate localCert = null;
		UesResult u ;
		EncryptContext encryptContext =initEncryptContext( "13611647802",null );
		test t = new test();
		u = t.getEncryptResult(encryptContext);
		System.out.print( u.getData() );
	}
//加密需要加密的内容
//	public EncryptParameter Encrypt(EncryptContext ctx,X509Certificate cert) throws InvocationTargetException
//	{
//		encryptParameter.setTransportData(useclient.generateEncryptData(ctx.getPlainData(), RSA, cert));
//		return encryptParameter;
//	}
//
//	public EncryptData Encryptnumber(EncryptContext ctx,X509Certificate cert) throws InvocationTargetException
//	{
//		EncryptData data =useclient.generateEncryptData(ctx.getPlainData(), RSA,cert);
//		return data;
//	}


	public UesResult getEncryptResult(EncryptContext ctx)throws Exception{
		EncryptParameter param = this.generateParam(ctx, (X509Certificate)null);
		UesResult result = this.uesRemoteService.saveDataByParam(param);
		return result;
	}


	public EncryptParameter generateParam(EncryptContext ctx, X509Certificate cert) throws InvocationTargetException {
		Validate.notNull(ctx.getPlainData(), "save.plain");
		EncryptParameter param = new EncryptParameter();
		param.setTransportData(useclient.generateEncryptData(ctx.getPlainData(), ctx.getEncryptType(), cert));
		param.setDigest(ctx.isDigest());
		if(ctx.getSummariable() != null) {
			param.setSummariable(ctx.getSummariable().generateParameter());
		}

		param.setTemporarily(ctx.getTemporarily());
		return param;
	}

	private static EncryptContext initEncryptContext(String src, String summary) {
		EncryptContext ctx = new EncryptContext(src);
		ctx.withDigest();
		ctx.withSummariable(new EchoSummary(summary));
		ctx.asEncryptType( EncryptType.RSA);
		return ctx;
	}

}
