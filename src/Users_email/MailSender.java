package Users_email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private String fromEmail="ledaulli@gmail.com";
	private String password="shuria40";

	public void MailSend(String name, String pwd, String email){
		String sub=name +"���� ��й�ȣ";
		String content="<html><head><meta charset='utf-8'></head><body>"+ name +"���� ��й�ȣ��......<br><br><br><br><br> ["+ pwd +"]�Դϴ�.</body></html>";
		try{
			Properties props=new Properties();
			props.put("mail.smtp.starttls", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			
			MyAuth auth=new MyAuth(fromEmail, password);
			Session session=Session.getDefaultInstance(props, auth);
			//�޼��� ���� ó��
			MimeMessage msg=new MimeMessage(session);
			msg.setHeader("content-type", "text/plain;charset=utf-8");
			//���� ���
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, name, "utf-8"));
			//���� ����
			msg.setSubject(sub);
			//���� ����
			msg.setContent(content, "text/html;charset=utf-8");
			msg.setSentDate(new java.util.Date());
			Transport.send(msg);
			
		}catch(UnsupportedEncodingException ue){
			System.out.println(ue.getMessage());
			
		}catch(MessagingException me){
			System.out.println(me.getMessage());
			
		}
	}
}
