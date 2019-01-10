package app.tests;

import javax.mail.MessagingException;

import app.works.MailUtil;

public class MailTest {
	public static void main(String[] args) {
		try {
			MailUtil.send_mail("244305150@qq.com", String.valueOf(Math.random() * 999));
			System.out.println("邮件发送成功!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
