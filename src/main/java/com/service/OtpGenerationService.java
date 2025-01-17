package com.service;

import org.springframework.stereotype.Service;

@Service
public class OtpGenerationService {
	
	public String GeneratOtp(int size) {
		
		StringBuilder otp = new StringBuilder("");
		
		String seed = "1234567890";
		
		for(int i = 1;i<=size;i++) {
			int index = (int) (Math.random()*seed.length());
			
			if(i==0 && index==1) {
				i--;
			}
			otp.append(seed.charAt(index));
		}
		
		
		
		
		return otp.toString();
		
	}

}
