package com.common;

/**
 * Created by hanxiaodi on 18/4/26.
 */

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * <p>签名数据</p>
 * @author fjl
 * @version $Id: SignData.java, v 0.1 2013-11-12 下午2:42:59 fjl Exp $
 */
public class SignData implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 448541510590995103L;
	/**
	 * 合作商户id
	 */
	private String partnerId;
	/**
	 * 签名串
	 */
	private String sign;

	/**
	 * 签名算法
	 */
	private String signType;

	/**
	 * 签名版本号
	 */
	private String signVersion;

	/**
	 * 签名原字符串
	 */
	private String signSrc;

	/**
	 * 字符集
	 */
	private String charset;

	public String getSignVersion() {
		return signVersion;
	}

	public void setSignVersion(String signVersion) {
		this.signVersion = signVersion;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignSrc() {
		return signSrc;
	}

	public void setSignSrc(String signSrc) {
		this.signSrc = signSrc;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

