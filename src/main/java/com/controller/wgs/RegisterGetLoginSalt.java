package com.controller.wgs;

import com.wgsBean.RegisterBean;
import com.wgsBean.WgsBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by hanxiaodi on 17/12/4.
 */
@Controller
public class RegisterGetLoginSalt
{
	@RequestMapping(value = "RegisterGetLoginSalt")
	public static String sendPost(@ModelAttribute("wgsBaseBean") WgsBean wgsBaseBean,
			@ModelAttribute("rgsBean") RegisterBean rgsBean){
		String temp;
		BufferedReader	br = null;
		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer=null;
		try{
			StringBuffer params = new StringBuffer();
			rgsBean.getMobile();

			URL url = new URL( "http://10.65.209.17:9100/wexwallet/gateway.do" );
			URLConnection con = url.openConnection();
			con.setRequestProperty( "accept", "*/*" );
			con.setRequestProperty( "connection", "Keep-Alive" );
			con.setRequestProperty( "content-Type", "application/x-www-form-urlencoded" );
			con.connect();
			con.setDoInput( true );
			con.setDoInput( true );

			OutputStreamWriter osw = new OutputStreamWriter( con.getOutputStream(), "UTF-8" );
			if(params!=null&&params.length()>0){
				osw.write( params.substring( 0,params.length()-1 ) );
				osw.flush();
			}

			int contentLength = Integer.parseInt( con.getHeaderField( "Content-Length" ) );
			if(contentLength>0){
				br = new BufferedReader( new InputStreamReader( con.getInputStream(), Charset.defaultCharset() ) );

			}while((temp=br.readLine())!=null){
				resultBuffer.append( temp );
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return resultBuffer.toString();
	}

}


