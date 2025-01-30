package com.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.entity.AccountEntity;
import com.entity.UserEntity;
import com.repository.AccountRepository;
import com.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccountController {

	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private HttpSession session;
	
	
	@GetMapping("addaccount")
	public String addAccount() {
		return "AddAccount";
	}

	
//	@PostMapping("saveaccount")
//	public String saveAccount(AccountEntity account) {
//		
//		UUID userId = (UUID) session.getAttribute("userId");
//	    Optional<UserEntity> optUser = userRepo.findById(userId);
//		
//		if(optUser.isPresent()) {
//			UserEntity user = optUser.get();
//			 user.addAccount(account);
//			accountRepo.save(account);
//			return "redirect:/listaccount";
//		}else {
//	        
//	        return "redirect:/addaccount?error=userNotFound";
//			
//		}
//	}
//	
	
	
	@PostMapping("saveaccount")
	public String saveAccount(AccountEntity account) {
		
		UUID userId = (UUID) session.getAttribute("userId");
		Optional<UserEntity> optUser = userRepo.findById(userId);
		
		if(optUser.isPresent()) {
			account.setUser(optUser.get());
			accountRepo.save(account);
			return "redirect:/listaccount";
		}else {
	        
	        return "redirect:/addaccount?error=userNotFound";
			
		}
	}
	@GetMapping("listaccount")
	public String listAccount(Model model) {
		List<AccountEntity> accounts = accountRepo.findAll();
		model.addAttribute("accounts",accounts);
		return "ListAccount";
	}
	
	
//	@GetMapping("deleteaccount")
//	public String deleteAccount(@RequestParam ("id") Integer accountId) {
//		
//		 Optional<AccountEntity> optAccount = accountRepo.findById(accountId);
//		    if (optAccount.isPresent()) {
//		        AccountEntity account = optAccount.get();
//		        UserEntity user = account.getUser();
////		        user.removeAccount(account); 
//		accountRepo.deleteById(accountId);
//		return "redirect:/listaccount";
//	}
	
	
	@GetMapping("deleteaccount")
	public String deleteAccount(@RequestParam ("id") Integer accountId) {
		accountRepo.deleteById(accountId);
//		session.invalidate();
		return "redirect:/listaccount";
	}
	
	@GetMapping("editaccount")
	public String editAccount(@RequestParam Integer id,Model model) {
		Optional <AccountEntity> optional = accountRepo.findById(id);
		if(optional.isEmpty()) {
			return "redirect:/listaccount";
		}
		AccountEntity account = optional.get();
		model.addAttribute("account",account);
		return "EditAccount";
	}
	
//	@PostMapping("updateaccount")
//	public String updateAccount(AccountEntity account){
//		UUID userId = (UUID) session.getAttribute("userId");
//		Optional<UserEntity> optUser = userRepo.findById(userId);
//		UserEntity user = optUser.get();
//		 user.addAccount(account);
//		accountRepo.save(account);
//		return "redirect:/listaccount";
//		
//	}
	
//	@PostMapping("updateaccount")
//	public String updateAccount(AccountEntity account, Model model) {
//	    UUID userId = (UUID) session.getAttribute("userId");
//
//	    if (userId == null) {
//	        model.addAttribute("error", "User not logged in.");
//	        return "redirect:/login";  // Redirect to the login page or show an error
//	    }
//
//	    Optional<UserEntity> optUser = userRepo.findById(userId);
//	    
//	    if (optUser.isPresent()) {
//	        UserEntity user = optUser.get();
//	        user.addAccount(account);
//	        accountRepo.save(account);
//	        return "redirect:/listaccount";
//	    } else {
//	        model.addAttribute("error", "User not found.");
//	        return "redirect:/login";  // Handle case when the user is not found
//	    }
//	}
	
	
	
	@PostMapping("updateaccount")
	public String updateAccount(AccountEntity account,  Model model){
		

		UUID userId = (UUID) session.getAttribute("userId");
		
		if (userId == null) {
	        model.addAttribute("error", "User not logged in.");
	        return "redirect:/login"; 
	        }
		
		Optional<UserEntity> optUser = userRepo.findById(userId);
		account.setUser(optUser.get());
		accountRepo.save(account);
		return "redirect:/listaccount";
		
	}
	

	

}
