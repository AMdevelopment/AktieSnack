package se.amdev.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public static int threadYesterday = 0;
	public static int stockYesterday = 0;
	public static int userYesterday = 0;
	public static int postYesterday = 0;
	
	public static void sendInfo(String email) {
		InternetAddress[] addresses = null;
		try {
			addresses = InternetAddress.parse(email);
		}
		catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties props = new Properties();
		props.put("mail.smtp.host", "send.one.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator()
				{
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("info@stockmonkey.se", "Sammasak");
					}
				});
		try {
			String text = "Varmt välkommen!" +
					"\n\nTo unsubscribe, please click this link: <a data-method='delete' href="
					+ "http://" + email + ".unsubscribe.stockmonkey.se" + ">UNSUBSCRIBE</a>";

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@stockmonkey.se"));
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setSubject("Välkommen till StockMonkey.se");
			message.setContent(text, "text/html; charset=utf-8");
			message.addHeader("Content-type", "text/html; charset=UTF-8");

			Transport.send(message);
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sendError(String where, Exception ex) {
		InternetAddress[] addresses = null;
		try {
			addresses = InternetAddress.parse("m.falkurt@gmail.com");
		}
		catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "send.one.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("info@stockmonkey.se","Sammasak");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@stockmonkey.se"));
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setSubject("SERVER ERROR @ " + where);
			message.setText("Problemos på servern " + ex.toString());

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sendDaily(int threadSize, int stockSize, int userSize, int postSize) {
		InternetAddress[] addresses = null;
		
		try {
			addresses = InternetAddress.parse("m.falkurt@gmail.com");
		}
		catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "send.one.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("info@stockmonkey.se","Sammasak");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@stockmonkey.se"));
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setSubject("DAGLIG UPPDATERING");
			message.setText("Hej! \n\nJust nu har vi exakt \n" + 
					threadSize + " antal trådar,\n" + 
					userSize + " antal användare,\n" + 
					stockSize + " antal aktier\noch " + 
					postSize + " antal inlägg på AktieSnack.se." + 
					"\n\nDetta är en ökning med \n" + (threadSize - threadYesterday) + " antal trådar,\n"
												+ (userSize - userYesterday) + " antal användare,\n"
												+ (stockSize - stockYesterday) + " antal aktier,\n"
												+ (postSize - postYesterday) + " antal inlägg från igår.\n"
					+ "\nHoppas att detta gjorde dig glad!\n MVH, Servern.");

			Transport.send(message);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		threadYesterday = threadSize;
		stockYesterday = stockSize;
		userYesterday = userSize;
		postYesterday = postSize;
	}
}