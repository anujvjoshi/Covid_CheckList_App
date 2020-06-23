package com.covidchecklist.app.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:email.properties")
@Data
public class EmailProperties {
	
	@Value( "${spring.mail.host}" )
	private String smtpHost;
	
	@Value( "${spring.mail.port}" )
	private String smtpPort;
	
	@Value( "${mail.smtp.socketFactory.port}" )
	private String socketFactoryPort;
	
	@Value( "${mail.smtp.socketFactory.class}" )
	private String socketFactoryClass;
	
	@Value( "${mail.smtp.auth}" )
	private String smtpAuth;
	
	@Value( "${mail.smtp.starttls.enable}" )
	private String startTlsEnable;
	
	@Value( "${spring.mail.username}" )
	private String username;
	
	@Value( "${spring.mail.password}" )
	private String password;
	

}
