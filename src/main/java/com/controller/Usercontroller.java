package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.UserEntity;
import com.repository.UserRepository;
import com.service.GenerateRandomValueService;
import com.service.MailSenderService;
import com.service.OtpGenerationService;
import jakarta.servlet.http.HttpSession;

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
	
	@Autowired
	GenerateRandomValueService generateRandomValue;
	
	@Autowired
   private HttpSession session;
    
    
  
	@GetMapping("signup")
	public String signup() {
		return "SignUp";
	}


	 @PostMapping("saveuser")
	    public String saveUser(UserEntity user) {      
		  	userRepository.save(user);
	        return "redirect:/login";
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
	public String login(@RequestParam String email, @RequestParam String password, Model model) {

	    Optional<UserEntity> optUser = userRepository.findByEmail(email);
		
		    if (optUser.isPresent() && optUser.get().getPassword().equals(password)) {
	        // Optionally, use passwordEncoder if passwords are encoded
	        // if (optUser.isPresent() && passwordEncoder.matches(password, optUser.get().getPassword())) {
	        session.setAttribute("userId", optUser.get().getUserId());
	        model.addAttribute("message", "Login successful!");
	        return "HomePage";  
	    } else {
	        model.addAttribute("error", "Invalid email or password");
	        return "Login";  
	    }
	}

	

	@GetMapping("forgotpassword")
	public String forgetpassword() {
		return "ForgetPassword";

	}

	@PostMapping("confirmEmail")
	public String ConfirmEmail(@RequestParam String email, Model model) {
		Optional<UserEntity> optUser = userRepository.findByEmail(email);
		if (!optUser.isPresent()) {  // Check if user is present
	        model.addAttribute("email", email);
	        model.addAttribute("error", "Invalid Credentials");
	        return "HomePage";
		}else {   
			String otp = otpGenerator.GeneratOtp(6);
			userRepository.updateOtpByEmail(email, otp);
			mailSender.sendMailForOtp(email, otp);
			System.out.println("Mail sent");
			model.addAttribute("email", email);
			model.addAttribute("sucess", "Mail Sent");
		}

		return "ResetPassword";

	}

	
	@PostMapping("UpdatePassword")
	public String updatePassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword,
	        Model model) {

	    Optional<UserEntity> optUser = userRepository.findByEmail(email);
	    if (optUser.isPresent()) {  
	        UserEntity user = optUser.get(); 
	        
	       
	        if (user.getOtp() != null && user.getOtp().equals(otp)) {
//	        	String newEncodedPassword = passwordEncoder.encode(newPassword);
//				user.setPassword(newEncodedPassword);
	            user.setPassword(newPassword); 
	            user.setOtp(null);  

	            userRepository.save(user);  
	            model.addAttribute("sucess", "Password successfully reset!"); 
	            return "Login";
	        } else {
	            model.addAttribute("error", "Invalid OTP. Please try again.");
	            return "ResetPassword";
	        }
	    } else {
	        model.addAttribute("error", "User not found.");
	        return "ForgetPassword";
	    }
	}

	
	@GetMapping("resetpassword")
	public String resetpassword(@RequestParam String token,Model model) {
			System.out.println(token);
		model.addAttribute("token",token);
		return "setResetPasswordLink";

	}
	
	
	
	
	@PostMapping("resetpassword")
	public String resetPassword(@RequestParam String confirmpassword, 
	                            @RequestParam String token, 
	                            UserEntity user, 
	                            Model model) {

	   
	    if (!user.getPassword().equals(confirmpassword)) {
	        model.addAttribute("error", "Passwords do not match. Please try again.");
	        return "ResetPasswordViaLink";
	    }

	   
	    Optional<UserEntity> optUser = userRepository.findByToken(token);
	    
	    if (optUser.isEmpty()) {
	        model.addAttribute("error", "Invalid or expired token. Please try again.");
	        return "ResetPasswordViaLink";
	    }

	   
	    UserEntity dbUser = optUser.get();
	    
	  
	    // String encodedPassword = passwordEncoder.encode(user.getPassword());
	    dbUser.setPassword(user.getPassword());  // or use encodedPassword
	    
	    
	    dbUser.setOtp("-1");
	    dbUser.setToken(null); 
	    
	   
	    userRepository.save(dbUser);
	    
	    model.addAttribute("msg", "Password updated successfully. You can now log in with your new password.");
	    
	    return "Login";
	}

	
	@PostMapping("setResetPasswordLink")
	public String setResetPasswordLink(Model model,UserEntity user) {
		
	    Optional<UserEntity> optUser = userRepository.findByEmail(user.getEmail());
	    if (optUser.isPresent()) {  
	    	String token = generateRandomValue.resetPasswordLink();
	    	UserEntity dbUser = optUser.get(); 
	    	mailSender.sendMailForResetPasswordLinkl(dbUser.getEmail(), dbUser.getFirstName(), token);
	    	dbUser.setToken(token);
	    	userRepository.save(dbUser);
	    }
		model.addAttribute("msg","Instructions to reset your password have been sent to your registered email address. Please check your inbox and follow the steps to proceed.");
		return "Login";
		
	}
		
}
