package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	private String emailDestinatario;
	private String assunto;
	private String msg;
	
	public Email() {
		// TODO Auto-generated constructor stub
	}
	
	public Email(String emailDestinatario, String assunto, String msg) {
		this.emailDestinatario = emailDestinatario;
		this.assunto = assunto;
		this.msg = msg;
	}
	
    public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean enviarEmail(Email email) {
        boolean retorno = false;
        //Get the session object  
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        

        Session s = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("interdiprojeto@gmail.com", "Projeto@123");//email e senha usuário 
                    }
                });

        //compose message  
        try {
            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress("interdiprojeto@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailDestinatario()));

            message.setSubject(email.getAssunto());
            message.setContent(email.getMsg(), "text/html; charset=utf-8");

            //send message  
            Transport.send(message);

            retorno = true;

        } catch (MessagingException e) {
            retorno = false;
            e.printStackTrace();
        }
        return retorno;
    }
	
	
}
