package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.MailUtil;

@Controller
public class ManualBankDataTask extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter out;
//	private String filePath = "/Users/sunjie/Desktop";
	private String filePath = "/opt";
	
	@RequestMapping(value = "executeManualBankDataTask")
	public void executeManualBankDataTask (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String hours = request.getParameter("hours");
		response.setCharacterEncoding("UTF-8");
		
		if (!hours.isEmpty()) {
			BankDataTask bdt = new BankDataTask();
			MailUtil mu = new MailUtil();
			try {
				bdt.writeBankData(hours);
				System.out.println("手工启动任务!");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String nowdate = df.format(new Date());
				mu.addAttachfile(filePath + "/bankdata/" + nowdate + "测试数据.xls");
				mu.sendHtmlEmailWithAttachment(nowdate + "测试数据", nowdate + "测试数据"
						+ ", 请清算同学们查收!<br/>", "qc@weibopay.com,csc_team@weibopay.com");
				out = response.getWriter();
				out.write("任务启动成功!邮件已发送");
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
