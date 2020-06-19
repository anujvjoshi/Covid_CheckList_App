package com.covidchecklist.app.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailSendService")
public class EmailSendService 
{
    @Autowired
    private JavaMailSender mailSender;
      
  
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo("ganesh.muluk@sydac.com");
        message.setFrom("ganesh.muluk@sydac.com");
        message.setSubject("Testing the Spring Boot Email");
        message.setText("First successful Email using the Spring Boot");
        
        return message;
    }
    //Method to compose and send the Email
    public void sendEMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
  
  //Method to send the Pre Configured Email
    public void sendPreConfiguredMail(String message) 
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage( emailTemplate());
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
    
  //  @PostConstruct
    void testMail() {
    	System.out.println("EmailSendService.testMail() -------------> ");
    	sendPreConfiguredMail("This is test mail..!");
    	System.out.println("EmailSendService.testMail() =============> ");
    }
}