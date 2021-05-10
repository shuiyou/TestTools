package com.common;



import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.weihui.common.lang.StringUtil;
import com.weihui.common.lang.SystemUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanxiaodi on 18/6/29.
 */
public class HttpNotifyReceiver
{
	static final Logger LOGGER = LoggerFactory.getLogger( HttpNotifyReceiver.class );
	static HttpServer server;
	static String url;
	static Map<String,Map<String,String>> messages;



	public  static void start(int port,String path,NotifyHander hander) throws IOException{
		server = HttpServer.create(new InetSocketAddress( port ),0);
		server.createContext( path,new CollectionHander( hander ) );
		server.start();
		url = String.format("http://%s:%d%s", SystemUtil.getHostInfo().getAddress(), port, path);
		messages = new HashMap<String, Map<String, String>>();
		LOGGER.info( "开始监听:{}",url );

	}

	public static void stop() {
		if (server != null) {
			server.stop(0);
			LOGGER.info("停止监听: {}", url);
			server = null;
			url = null;
			messages = new HashMap<String, Map<String, String>>();
		}
	}

	public static String getUrl() {
		return url;
	}

	/**
	 * 消费一个消息
	 * @param key 消息索引
	 * @return 如果消息不存在返回null
	 */
	public static Map<String, String> consume(String key) {
		return messages.remove(key);
	}

	/**
	 * 消费一个消息，返回校验器
	 * @param key 消息索引
	 * @return 校验器
	 */
	public static MessageAssert consumeAsserter(String key) {
		return new MessageAssert(messages.remove(key));
	}




	public  interface NotifyHander {
		/**
		 * 返回索引主键
		 * @param params post请求参数
		 * @return 索引主键
		 *
		 * */
		String buildKey(Map<String,String> params);


		/**
		 * 返回响应
		 * @return 响应
		 *
		 * */
		String getResponse(Map<String,String> params);
	}



	 private static  class CollectionHander implements HttpHandler{
		 NotifyHander notifyHander ;
		 public CollectionHander(NotifyHander notifyHander){
		 	this.notifyHander =notifyHander;
		 }
		 @Override public void handle(HttpExchange httpExchange) throws IOException
		 {
			try
			{
				Map<String,String> params =  getParameters(httpExchange);
				LOGGER.info( "收到信息:",params );
				String key = notifyHander.buildKey( params );
				LOGGER.info("消息索引",key);
				Map<String,String> exist = messages.put( key,params );
				if(exist!=null){
					LOGGER.info("");
				}
				String response = notifyHander.getResponse(params);
				httpExchange.sendResponseHeaders( 200,0 );
				OutputStream os = httpExchange.getResponseBody();
				os.write( response.getBytes() );
				os.close();
			}catch (Throwable e){
				LOGGER.info( "消息异常",e );
			}


		 }



		 //获取请求参数
		 private static Map<String,String> getParameters(HttpExchange httpexchange) throws IOException{
			 Map<String,String> parameters = new HashMap<String, String>();
			 InputStream inputStream = httpexchange.getRequestBody();
			 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			 byte[] buffer = new byte[2048];
			 int read = 0;
			 while((read =inputStream.read(buffer))!=-1){
				 byteArrayOutputStream.write(buffer, 0, read);
			 }
			 String[] KeyValuePairs = byteArrayOutputStream.toString().split( "&" );
			 for(String KeyValuePair:KeyValuePairs){
				 String[] KeyValue = KeyValuePair.split("=");
				 if(KeyValue.length !=2){
					 continue;
				 }
				 parameters.put( KeyValue[0], URLEncoder.encode( KeyValue[1],"UTF-8" ));
			 }
			 return parameters;
		 }
	 }


	 /**
	  * 消息验证器
	  * */

	 public static class MessageAssert{
	 	final Map<String,String> params;

		 public  MessageAssert(Map<String,String> params){
		 	this.params = params;
		 }

		 public Map<String,String> getParams(){
		 	return params;
		 }

		 /**
		  * 校验存在一个参数
		  * */
		 public MessageAssert existParam(){
			 Assert.assertTrue("存在参数",params!=null&&params.size()>0);
			 return this;
		 }

		 public MessageAssert param(String name,String value){
		 	existParam();
		 	Assert.assertEquals("参数"+name,value,params.get(name));
			params.remove( name );
			 return this;
		 }

		 /**
		  * 校验参数值
		  * */
		 public MessageAssert paramNotBlank(String name){
		 	Assert.assertTrue( "参数非空"+ name, StringUtil.isNotBlank( params.get(name)));
			 return  this;
		 }

		 /**
		  *  没有其他更多的参数
		  * */
		 public void noMore(){
		 	Assert.assertTrue( "无更多参数"+params,params == null || params.isEmpty() );
		 }
	 }

}
