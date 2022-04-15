package com.email.emailapi.service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	public boolean sendEmail(String subject, String message, String to) {
		boolean flag = false;
		
		String from = "singhanubhav9922@gmail.com";
		
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println(properties);

		// setting important information to properties oject

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: To get the session object..
		// session ke paas factory method hai jo object provide karega
		// isme do chiz pass kar deni hai - properties and authenticator(to authenticate
		// that my user name and password is correct)
		// Like a database connection, a mail session is a resource
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("singhanubhav9922@gmail.com", "Anubhav@1010");
			}

		});

		// debug session
		session.setDebug(true);

		// Step 2: compose the message [text, attachment, multimedia... etc]
		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			// from email
			mimeMessage.setFrom(from);

			// adding recipient to message
			// ye message jayega kisko
			// address parameter me internet address use karna hai
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			mimeMessage.setSubject(subject);

			// adding text to message
			mimeMessage.setText(message);

			// send
			// Step 3: send message using Transport class
			Transport.send(mimeMessage);

			System.out.println("sent..............successfully...");
			flag=true;
		} catch (Exception e) {
			e.printStackTrace(); // give proper message
		}
		return flag;
	}
}
