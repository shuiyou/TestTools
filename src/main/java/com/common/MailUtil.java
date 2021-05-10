package com.common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {

	// 设置服务器
	private static String KEY_SMTP = "mail.smtp.host";
	private static String VALUE_SMTP = "smtp.exmail.qq.com";
	// 服务器验证
	private static String KEY_PROPS = "mail.smtp.auth";
	// 发件人用户名、密码
	private String SEND_USER = "sunjie@weibopay.com";
	private String SEND_UNAME = "sunjie@weibopay.com";
	private String SEND_PWD = "talent7210";
	// 建立会话
	private MimeMessage message;
	private Session s;

	private String filename = "";
	@SuppressWarnings("rawtypes")
	private Vector file = new Vector();

	/*
	 * 初始化方法
	 */
	public MailUtil() {
		Properties props = System.getProperties();
		props.setProperty(KEY_SMTP, VALUE_SMTP);
		props.put(KEY_PROPS, "true");
		// props.put("mail.smtp.auth", "true");
		s = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
			}
		});
		s.setDebug(true);
		message = new MimeMessage(s);
	}

	/**
	 * 发送邮件
	 * 
	 * @param headName
	 *            邮件头文件名
	 * @param sendHtml
	 *            邮件内容
	 * @param receiveUser
	 *            收件人地址
	 */
	public void sendHtmlEmail(String headName, String sendHtml,
			String receiveUsers) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(SEND_USER);
			message.setFrom(from);
			// 收件人
			String to[] = receiveUsers.split(",");
			String toList = getMailList(to);
			new InternetAddress();
			InternetAddress[] iaToList = InternetAddress.parse(toList);
			message.setRecipients(Message.RecipientType.TO,iaToList);
			// 邮件标题
			message.setSubject(headName);
			String content = sendHtml.toString();
			// 邮件内容,也可以使纯文本"text/plain"
			message.setContent(content, "text/html;charset=GBK");
			message.saveChanges();
			Transport transport = s.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("send success!");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void addAttachfile(String fname) {
		file.addElement(fname);
	}

	public void sendHtmlEmailWithAttachment(String headName, String sendHtml,
			String receiveUsers) throws UnsupportedEncodingException {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(SEND_USER);
			message.setFrom(from);
			// 收件人列表
			String to[] = receiveUsers.split(",");
			String toList = getMailList(to);
			new InternetAddress();
			InternetAddress[] iaToList = InternetAddress.parse(toList);
			message.setRecipients(Message.RecipientType.TO,iaToList);
			// 邮件标题
			message.setSubject(headName);
			String content = sendHtml.toString();
			// 邮件内容,也可以使纯文本"text/plain"
			message.setContent(content, "text/html;charset=UTF-8");
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(content.toString(), "text/html;charset=UTF-8");
			mp.addBodyPart(mbp);
			if (!file.isEmpty()) {// 有附件
				@SuppressWarnings("rawtypes")
				Enumeration efile = file.elements();
				while (efile.hasMoreElements()) {
					mbp = new MimeBodyPart();
					filename = efile.nextElement().toString(); // 选择出每一个附件名
					FileDataSource fds = new FileDataSource(filename); // 得到数据源
					mbp.setDataHandler(new DataHandler(fds)); // 得到附件本身并至入BodyPart
					mbp.setFileName(MimeUtility.encodeText(fds.getName())); // 得到文件名同样至入BodyPart
					mp.addBodyPart(mbp);
				}
				file.removeAllElements();
			}
			message.setContent(mp);// Multipart加入到信件
			message.setSentDate(new Date());// 设置信件头的发送日期
			// 发送信件
			message.saveChanges();
			Transport transport = s.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("send success!");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		MailUtil se = new MailUtil();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowdate = df.format(new Date());
		se.addAttachfile("/Users/sunjie/Desktop" + "/bankdata/" + nowdate + "测试数据.xls");
		se.sendHtmlEmailWithAttachment(nowdate + "测试数据", nowdate + "测试数据"
				+ ", 请清算同学们查收!<br/>", "sunjie@weibopay.com");
	}

}
