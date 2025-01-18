package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.UserEntity;
import com.repository.UserRepository;
import com.service.MailSenderService;
import com.service.OtpGenerationService;



@Controller
public class Usercontroller {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	OtpGenerationService otpGenerator;
	
	@Autowired
	MailSenderService mailSender;
	
	@GetMapping("signup")
	public String signup() {
		return "SignUp";
	}
	
	@PostMapping("saveuser")
	public String saveuser(UserEntity user) {
		
		//Encoding password
		
//		String epassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(epassword);
//		System.out.println("Encoded password set in db");
		
		
	// saving it to database	
	userRepository.save(user);
	
	
	return "Login";
}
	
	@GetMapping("login")
	public String login() {
		return "Login";
	}
	
	
	
	
	
	@GetMapping("home")
	public String home() {
		return "HomePage";
	}
	@PostMapping("login")
	public String login(@RequestParam String email,@RequestParam String password,Model model) {
		
	UserEntity user =userRepository.findByEmail(email);
	
	if(user != null && user.getPassword().equals(password)) {
//	if(user != null && passwordEncoder.matches(password, user.getPassword())) {
		 model.addAttribute("message", "Login successful!");
		 return "Login";
	}else {
        
        model.addAttribute("error", "Invalid email or password");
        return "SignUp";
    }
	
}
	@GetMapping("forgotpassword")
	public String forgetpassword() {
		return "ForgetPassword";

	}
	
	@PostMapping("confirmEmail")
	public String ConfirmEmail(@RequestParam String email,Model model) {
		UserEntity user =userRepository.findByEmail(email);
		if(user == null) {
			model.addAttribute("email",email);
			model.addAttribute("error","Invalid Credentials");
			return "HomePage";	
		}else {
			String otp = otpGenerator.GeneratOtp(6);
			userRepository.updateOtpByEmail(email,otp);
			mailSender.sendMailForOtp(email, otp);
			System.out.println("Mail sent");
			model.addAttribute("email", email);
			model.addAttribute("sucess","Mail Sent");
		}
		
		return "ResetPassword";
		
	}
	
	
	
	@PostMapping("resetPassword")
	public String resetPassword(@RequestParam String email,@RequestParam String otp,@RequestParam String newPassword,Model model) {
		
		UserEntity user = userRepository.findByEmail(email);
		if(user != null) {
			if(user.getOtp() != null && user.getOtp().equals(otp)) {
//				String newEncodedPassword = passwordEncoder.encode(newPassword);
//				user.setPassword(newEncodedPassword);
				user.setPassword(newPassword);
				user.setOtp(null);
				
				userRepository.save(user);
				model.addAttribute("sucess", "Password successfully reset!");
				return "Login";
			}else {
				model.addAttribute("error", "Invalid OTP. Please try again.");
				return "ResetPassword";
			}
		}else {
	        model.addAttribute("error", "User not found.");
	        return "ForgetPassword";
		
		
	} 
	

	}
}
