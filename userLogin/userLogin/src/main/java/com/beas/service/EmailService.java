package com.beas.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	Environment env;

	public void sendVerificationEmail(String to, String username) {
		String subject = "Verify your email";
		String verificationUrl = env.getProperty("ip")+"?username=" + username;

		String message = "Click the link to verify your email: " + verificationUrl;

		sendEmail(subject, message, to);
	}

	public boolean sendEmail(String subject, String message, String to) {

		boolean f = false;

		String from = env.getProperty("spring.mail.username");

		String host = "mail.beas.co.in";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		// properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, env.getProperty("spring.mail.password")); //// Rasik bhita mail password

			}
		});

		session.setDebug(true);

		MimeMessage m = new MimeMessage(session);

		try {

			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);

			m.setContent(message, "text/html");

			Transport.send(m);

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}
}
