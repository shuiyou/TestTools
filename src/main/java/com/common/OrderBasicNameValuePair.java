package com.common;

import org.apache.http.message.BasicNameValuePair;

/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: OrderBasicNameValuePair.java, v 0.1 2013-11-19 下午4:58:36 fjl Exp $
 */
public class OrderBasicNameValuePair extends BasicNameValuePair implements Comparable<OrderBasicNameValuePair> {

	public OrderBasicNameValuePair(String name, String value) {
		super(name, value);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(OrderBasicNameValuePair other) {
		return this.getName().compareTo(other.getName());
	}

}

