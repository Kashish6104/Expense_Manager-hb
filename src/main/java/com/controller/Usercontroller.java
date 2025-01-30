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
import com.entity.UserEntity.Role;
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

	@GetMapping("/")
	public String login2() {
		return "Login";
	}

//	@PostMapping("login")
//	public String login(@RequestParam String email, @RequestParam String password, Model model) {
//
//	    Optional<UserEntity> optUser = userRepository.findByEmail(email);
//
//	    if (optUser.isPresent() && optUser.get().getPassword().equals(password)) {
//	        UserEntity loggedInUser = optUser.get(); // Get user from DB
//	        
//	        session.setAttribute("userId", loggedInUser.getUserId());
//	        model.addAttribute("message", "Login successful!");
//	        Role userRole = loggedInUser.getRole();
//	        if(userRole != null) {
//	        	System.out.println(userRole);
//	        }
//	        
//	        System.out.println(loggedInUser.getRole());
//	        System.out.println(loggedInUser.getFirstName());
//
//	        
//	        
//	        // Check if role is null
//	        if (userRole == null) {
//	            model.addAttribute("error", "User role is not assigned. Please contact admin.");
//	            System.out.println( "User role is not assigned. Please contact admin.");
//
//	            return "Login";
//	            
//	        }
//
//	        if (userRole.equals("USER")) {
//	        	System.out.println("I m USER ");
//	            return "HomePage";
//	        } else if (userRole.equals("ADMIN")) {
//	        	System.out.println("I m ADMIN ");
//
//	            return "AdminHome";
//	        }
//	    }
//
//	    model.addAttribute("error", "Invalid email or password.");
//        System.out.println( "Invalid email or password.");
//
//	    return "null"; // Ensure Login.jsp exists
//	}
	
	
	
	@PostMapping("login")
	public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {

	    Optional<UserEntity> optUser = userRepository.findByEmail(email);

	    if (optUser.isPresent() && optUser.get().getPassword().equals(password)) {
	        UserEntity loggedInUser = optUser.get(); // Get user from DB

	        session.setAttribute("userId", loggedInUser.getUserId());
	        model.addAttribute("message", "Login successful!");

	        Role userRole = loggedInUser.getRole(); // Assuming Role is an enum or class

	        if (userRole != null) {
	            System.out.println(userRole);
	        }

	        System.out.println(loggedInUser.getRole());
	        System.out.println(loggedInUser.getFirstName());

	        // Check if role is null
	        if (userRole == null) {
	            model.addAttribute("error", "User role is not assigned. Please contact admin.");
	            System.out.println("User role is not assigned. Please contact admin.");
	            return "Login"; // Return to login page in case of no role assigned
	        }

	        // Assuming Role is an enum, compare it like this:
	        if (userRole.equals(Role.USER)) {
	            System.out.println("I am USER");
	            return "HomePage";
	        } else if (userRole.equals(Role.ADMIN)) {
	            System.out.println("I am ADMIN");
	            return "AdminHome";
	        }
	    }

	    model.addAttribute("error", "Invalid email or password.");
	    System.out.println("Invalid email or password.");
	    return "Login"; // Return to login page on failure
	}


	
	@GetMapping("forgotpassword")
	public String forgetpassword() {
		return "ForgetPassword";

	}

	@PostMapping("confirmEmail")
	public String ConfirmEmail(@RequestParam String email, Model model) {
		Optional<UserEntity> optUser = userRepository.findByEmail(email);
		if (!optUser.isPresent()) { // Check if user is present
			model.addAttribute("email", email);
			model.addAttribute("error", "Invalid Credentials");
			return "HomePage";
		} else {
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
	public String resetpassword(@RequestParam String token, Model model) {
		System.out.println(token);
		model.addAttribute("token", token);
		return "setResetPasswordLink";

	}

	@PostMapping("resetpassword")
	public String resetPassword(@RequestParam String confirmpassword, @RequestParam String token, UserEntity user,
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
		dbUser.setPassword(user.getPassword()); // or use encodedPassword

		dbUser.setOtp("-1");
		dbUser.setToken(null);

		userRepository.save(dbUser);

		model.addAttribute("msg", "Password updated successfully. You can now log in with your new password.");

		return "Login";
	}

	@PostMapping("setResetPasswordLink")
	public String setResetPasswordLink(Model model, UserEntity user) {

		Optional<UserEntity> optUser = userRepository.findByEmail(user.getEmail());
		if (optUser.isPresent()) {
			String token = generateRandomValue.resetPasswordLink();
			UserEntity dbUser = optUser.get();
			mailSender.sendMailForResetPasswordLinkl(dbUser.getEmail(), dbUser.getFirstName(), token);
			dbUser.setToken(token);
			userRepository.save(dbUser);
		}
		model.addAttribute("msg",
				"Instructions to reset your password have been sent to your registered email address. Please check your inbox and follow the steps to proceed.");
		return "Login";

	}

}
