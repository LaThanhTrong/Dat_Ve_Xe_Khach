package main;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Authenticator;

public class MailAPI {
	private static String otp;
	
	public static void sendMail(String r) throws Exception {
		  System.out.println("Loading....");
		  Properties props = new Properties();
	      // Setup mail server
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	      props.put("mail.smtp.starttls.enable", "true");
	      String account = "elle322002@gmail.com";
	      String pass = "vyggsdwvudajghga";

	      // Get the default Session object.
	      Session session = Session.getInstance(props, new Authenticator(){
	    	  @Override
	    	  protected PasswordAuthentication getPasswordAuthentication() {
	    		  return new PasswordAuthentication(account,pass);
	    	  }
	      });
	      Message m = prepareMessage(session,account,r);
	      
	      Transport.send(m);
	      System.out.println("Success!");
	}
	private static Message prepareMessage(Session session, String account, String r) {
		try {
			Message m = new MimeMessage(session);
			m.setFrom(new InternetAddress(account));
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(r));
			m.setSubject("OTP");
			MailAPI.otp = RandomOTP.getNOTP(6);
			m.setText(MailAPI.otp);
			return m;
		}catch(Exception ex) {
			Logger.getLogger(MailAPI.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	public String getOTP() {
		return otp;
	}
} 