package com.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.common.DbEnviornment;
import com.common.MailUtil;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class BankDataTask implements ServletContextListener {

	private MailUtil mailUtil = new MailUtil();
	private Label recordDateLabel;
	private Label fundTypeLabel;
	private Label bankNameLabel;
	private Label channelCodeLabel;
	private Label executorLabel;
	private Label testTargetLabel;
	private Label orderAmountLabel;
	private Label bankOrderNoLabel;
	private Label orgFundinOrderNoLabel;
	private Label envTypeLabel;
//	private String filePath = "/Users/sunjie/Desktop";
	 private String filePath = "/opt";
	TimerTask task = null;
	Timer t = null;

	// private String path = this
	// .getClass()
	// .getResource("/")
	// .toString()
	// .substring(
	// 5,
	// this.getClass().getResource("/").toString()
	// .indexOf("/target"));

	public void writeBankData(String hours) throws IOException,
			RowsExceededException, WriteException, BiffException {
		Workbook wb = Workbook.getWorkbook(new File(filePath
				+ "/bankdata/demo/测试数据.xls"));// 获得原始文档
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowdate = df.format(new Date());
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filePath
				+ "/bankdata/" + nowdate + "测试数据.xls"), wb);// 创建一个可读写的副本
		WritableSheet sheet = workbook.getSheet(0);
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		String querySql = "select * from BANK_DATA where GMT_CREATE_TIME > sysdate - 1/24*"
				+ hours;
		DbEnviornment de = new DbEnviornment();
		de.Connect("10.65.193.12:1521", "whpay", "whqa", "whqa");
		int i = 5;
		try {
			ResultSet rs = DbEnviornment.statement.executeQuery(querySql);
			while (rs.next()) {
				recordDateLabel = new Label(1, i, rs.getString("RECORD_DATE"),
						wcf);
				sheet.addCell(recordDateLabel);
				fundTypeLabel = new Label(2, i, rs.getString("FUND_TYPE"), wcf);
				sheet.addCell(fundTypeLabel);
				bankNameLabel = new Label(3, i, rs.getString("BANK_NAME"), wcf);
				sheet.addCell(bankNameLabel);
				channelCodeLabel = new Label(4, i,
						rs.getString("CHANNEL_CODE"), wcf);
				sheet.addCell(channelCodeLabel);
				executorLabel = new Label(5, i, rs.getString("EXECUTOR"), wcf);
				sheet.addCell(executorLabel);
				executorLabel = new Label(5, i, rs.getString("EXECUTOR"), wcf);
				sheet.addCell(executorLabel);
				if (rs.getString("TEST_TARGET") != null) {
					testTargetLabel = new Label(6, i,
							rs.getString("TEST_TARGET"), wcf);
					sheet.addCell(testTargetLabel);
				}
				orderAmountLabel = new Label(8, i,
						rs.getString("ORDER_AMOUNT"), wcf);
				sheet.addCell(orderAmountLabel);
				bankOrderNoLabel = new Label(10, i,
						rs.getString("BANK_ORDER_NO"), wcf);
				sheet.addCell(bankOrderNoLabel);
				if (rs.getString("ORG_FUNDIN_ORDER_NO") != null) {
					orgFundinOrderNoLabel = new Label(11, i,
							rs.getString("ORG_FUNDIN_ORDER_NO"), wcf);
					sheet.addCell(orgFundinOrderNoLabel);
				}
				envTypeLabel = new Label(12, i, rs.getString("ENV_TYPE"), wcf);
				sheet.addCell(envTypeLabel);

				i++;
			}
			rs.close();
			com.common.DbEnviornment.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workbook.write();
		workbook.close();
	}

	@SuppressWarnings({ "resource", "unused" })
	private String createFile() {
		String oldPath = "/bankdata/demo/测试数据.xls";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowdate = df.format(new Date());
		String newPath = "bankdata/" + nowdate + "测试数据.xls";
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制文件操作出错!");
			e.printStackTrace();

		}
		return newPath;
	}

	public void contextInitialized(ServletContextEvent arg0) {

		task = new TimerTask() {
			public void run() {
				DbEnviornment de = new DbEnviornment();
				de.Connect("10.65.193.12:1521", "whpay", "whqa", "whqa");
				String querySql = "select count(*) from BANK_DATA where GMT_CREATE_TIME > sysdate - 1";
				try {
					ResultSet rs = DbEnviornment.statement
							.executeQuery(querySql);
					if (rs.next() && !rs.getString("COUNT(*)").equals("0")) {
						System.out.println("任务启动!开始收集数据并发送邮件!");
						writeBankData("24");
						Thread.sleep(30000);
						SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
						String nowdate = df.format(new Date());
						mailUtil.addAttachfile(filePath + "/bankdata/"
								+ nowdate + "测试数据.xls");
						mailUtil.sendHtmlEmailWithAttachment(nowdate + "测试数据",
								nowdate + "测试数据" + ", 请清算同学们查收!<br/>",
								"qc@weibopay.com,csc_team@weibopay.com");
					} else {
						System.out.println("任务启动!没有数据, 今日不发送邮件!");
					}
					rs.close();
					com.common.DbEnviornment.statement.close();
					com.common.DbEnviornment.con.close();
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		t = new Timer();
		// 一天的毫秒数
		long daySpan = 24 * 60 * 60 * 1000;

		// 规定的每天运行时间
		final SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd '23:50:00'");
		// 首次运行时间
		Date startTime;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf
					.format(new Date()));
			// 如果今天的已经过了 首次运行时间就改为明天
			if (System.currentTimeMillis() > startTime.getTime())
				startTime = new Date(startTime.getTime() + daySpan);

			// 如果今天的已经过了 首次运行时间就改为明天
			if (System.currentTimeMillis() > startTime.getTime())
				startTime = new Date(startTime.getTime() + daySpan);
			t.scheduleAtFixedRate(task, startTime, daySpan);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		t.cancel();
	}

	public static void main(String[] args) throws BiffException, IOException,
			InterruptedException, WriteException, ParseException {

	}
}