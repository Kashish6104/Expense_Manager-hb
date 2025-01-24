package com.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class GenerateRandomValueService {
	
	public String resetPasswordLink() {
		String data = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
		
		StringBuilder sb = new StringBuilder("");
		for (int i = 1; i<20;i++) {
			int index = (int) (Math.random()*data.length());
			sb.append(data.charAt(index));
			
		}
		
		
		return sb.toString();
		
	}
}
