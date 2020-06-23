package com.covidchecklist.app.smtp;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.dto.SurveyDetailsDTO;
import com.covidchecklist.app.properties.EmailProperties;
import com.sun.mail.smtp.SMTPMessage;

@Service
public class EmailService {

	@Autowired
	EmailProperties emailProperties;

	private Session getEmailSession() {
		Session session = Session.getInstance(getEmailProperties(), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailProperties.getUsername(), emailProperties.getPassword());
			}
		});
		return session;
	}

	private Properties getEmailProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.host", emailProperties.getSmtpHost());
		props.put("mail.smtp.socketFactory.port", emailProperties.getSocketFactoryPort());
		props.put("mail.smtp.socketFactory.class", emailProperties.getSocketFactoryClass());
		props.put("mail.smtp.auth", emailProperties.getSmtpAuth());
		props.put("mail.smtp.port", emailProperties.getSmtpPort());
		// enable STARTTLS
		props.put("mail.smtp.starttls.enable", emailProperties.getStartTlsEnable());
		return props;
	}

	public void sendSurveyEmail(String surveyId, String name, String email, Date date,
			List<SurveyDetailsDTO> surveyDetails) {

		String subject = "Submission # " + surveyId + " - Covid 19 checklist - " + name + " -  " + date;

		String ccEmail = emailProperties.getUsername();

		String bccEmail = "";

		StringBuffer body = new StringBuffer();

		body.append("<html> <meta charset=\"UTF-8\"><body>");

		body.append("<b>" + name + "</b> has submitted survey. </br>");

		body.append("<table width='100%' border='1' align='center'>"
				+ "<tr align='center' style=\"background: blue;text-fill:white;\">"
				+ "<td><b>Sr. No <b></td><td><b>Question <b></td>" + "<td><b>Answer<b></td>" + "</tr>");

		surveyDetails.forEach(s -> {
			body.append("<tr align='center'>" + "<td>" + (surveyDetails.indexOf(s) + 1) + " </td> <td align='left'>"
					+ s.getQuestion() + "</td>" + "<td>" + s.getAnswer() + "</td>" + "</tr>");
		});

		body.append("</table>");
		body.append("</body></html>");

		sendEmail(email, ccEmail, bccEmail, subject, body.toString());

	}

	private void sendEmail(String toEmail, String ccEmail, String bccEmail, String subject, String body) {
		try {
			Session session = getEmailSession();
			SMTPMessage message = new SMTPMessage(session);
			message.setFrom(new InternetAddress(emailProperties.getUsername()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

			if (!"".equals(ccEmail.trim())) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail));
			}

			if (!"".equals(bccEmail.trim())) {
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccEmail));
			}

			message.setSubject(subject);
			message.setContent(body, "text/html");
			message.setNotifyOptions(SMTPMessage.NOTIFY_FAILURE);
			int returnOption = message.getReturnOption();
			System.out.println(returnOption);
			Transport.send(message);
			System.out.println("sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}