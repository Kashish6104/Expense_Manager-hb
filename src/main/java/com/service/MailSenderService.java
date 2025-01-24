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
	
	
public void sendMailForResetPasswordLinkl(String email,String firstName,String token) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("Password Reset Instructions");

		mail.setText("Hello " + firstName + ",\n\n"
		        + "We received a request to reset your password. Please use the link below to proceed with resetting your password:\n\n"
		        +"http://localhost:9896/resetpassword?key="+ token + "\n\n"
		        + "If you did not request this password reset, please ignore this email.\n\n"
		        + "Best regards,\n"
		        + "Expense Manager");

		mail.setFrom("kspatel6104@gmail.com");
		javaMailSender.send(mail);

		
	}
	
}
