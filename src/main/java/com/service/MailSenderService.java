package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendMailForOtp(String email,String otp) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("Otp For Updating Password!!");
		mail.setText("Otp to Reset Password:"+otp);
		mail.setFrom("kspatel6104@gmail.com");
		javaMailSender.send(mail);
		
	}
	
}
