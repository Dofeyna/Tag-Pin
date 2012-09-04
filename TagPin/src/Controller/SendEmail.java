package Controller;

/**
 * @(#) Send E-Mail class: This class sends activation code via e-mail to recently created account
 *
 * 
 *
 * @Semih Akar 
 * @version 1.00 
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import Model.MySQLUser;

public class SendEmail {

	//Properties
	MySQLUser userDB;
	
	public void sendActivationCode( String email, String code) throws AddressException, MessagingException
	{
		//Defining Gmail Account
		String host = "smtp.gmail.com";
	    String from = "tagandpin";
	    String pass = "tagandpin1234";
	    Properties props = System.getProperties();
	    props.put( "mail.smtp.starttls.enable", "true");
	    props.put( "mail.smtp.host", host);
	    props.put( "mail.smtp.user", from);
	    props.put( "mail.smtp.password", pass);
	    props.put( "mail.smtp.port", "587");
	    props.put( "mail.smtp.auth", "true");

	    //Defining Receiver
	    String receiver = email;

	    //Creating Message
	    Session session = Session.getDefaultInstance( props, null);
	    MimeMessage message = new MimeMessage( session);
	    message.setFrom( new InternetAddress( from));

	    InternetAddress toAddress = new InternetAddress();
	    
	    toAddress = new InternetAddress( receiver);
	   
	    System.out.println( Message.RecipientType.TO);
  
	    message.addRecipient( Message.RecipientType.TO, toAddress);
	    
	    //Creating String for Email
	    message.setSubject( "Welcome to Tag&Pin");
	    message.setText( "Your Activation Code is : " + code);
	    Transport transport = session.getTransport("smtp");
	    transport.connect( host, from, pass);
	    transport.sendMessage( message, message.getAllRecipients());
	    transport.close();
	}
	
	public void sendPassword( String email) throws Exception,AddressException, MessagingException
	{
		//Connecting to Database
		userDB = new MySQLUser();
			
		//Defining Gmail Account
		String host = "smtp.gmail.com";
	    String from = "tagandpin";
	    String pass = "tagandpin1234";
	    Properties props = System.getProperties();
	    props.put( "mail.smtp.starttls.enable", "true");
	    props.put( "mail.smtp.host", host);
	    props.put( "mail.smtp.user", from);
	    props.put( "mail.smtp.password", pass);
	    props.put( "mail.smtp.port", "587");
	    props.put( "mail.smtp.auth", "true");

	    //Defining Receiver
	    String receiver = email;

	    //Creating Message
	    Session session = Session.getDefaultInstance( props, null);
	    MimeMessage message = new MimeMessage( session);
	    message.setFrom( new InternetAddress( from));

	    InternetAddress toAddress = new InternetAddress();
	    
	    toAddress = new InternetAddress( receiver);
	   
	    System.out.println( Message.RecipientType.TO);

	    message.addRecipient( Message.RecipientType.TO, toAddress);
	    
	    //Getting Password of User
	    String password;
	    password = userDB.getUserPassword( userDB.getUserID( email));
	    
	    //Creating String for Email
	    message.setSubject( "Tag&Pin");
	    message.setText( "Your Password Code is : " + password);
	    Transport transport = session.getTransport( "smtp");
	    transport.connect( host, from, pass);
	    transport.sendMessage( message, message.getAllRecipients());
	    transport.close();
	}
	
}
