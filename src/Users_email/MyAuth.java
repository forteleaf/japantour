package Users_email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuth extends Authenticator{
	private String fromEmail;
	private String password;
	
	public MyAuth(String fromEmail, String password) {
		this.fromEmail = fromEmail;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(fromEmail,password);
	}
}
