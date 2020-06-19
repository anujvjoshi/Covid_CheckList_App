package com.covidchecklist.app.smtp;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

   public static void main(String [] args) { 
  
      // Recipient's email ID needs to be mentioned.
      String to = "Sonal.Potdar@sydac.com";

      // Sender's email ID needs to be mentioned
      String from = "Sonal.Potdar@sydac.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.put("mail.smtp.host", host);  

      // Get the default Session object.
      Session session = Session.getInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

		  message.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      message.addHeader("format", "flowed");
	      message.addHeader("Content-Transfer-Encoding", "8bit");

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Now set the actual message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}