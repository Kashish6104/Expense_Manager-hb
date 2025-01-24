package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.entity.AccountEntity;
import com.repository.AccountRepository;
import com.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccountController {

	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("addaccount")
	public String addAccount() {
		return "AddAccount";
	}

	
	@PostMapping("saveaccount")
	public String saveAccount(AccountEntity account) {
		accountRepo.save(account);
		return "redirect:/listaccount";
	}
	
	@GetMapping("listaccount")
	public String listAccount(Model model) {
		List<AccountEntity> accounts = accountRepo.findAll();
		model.addAttribute("accounts",accounts);
		return "ListAccount";
	}
	
	
	@GetMapping("deleteaccount")
	public String deleteAccount(@RequestParam ("id") Integer accountId) {
		accountRepo.deleteById(accountId);
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
	
	@PostMapping("updateaccount")
	public String updateAccount(AccountEntity account){
		accountRepo.save(account);
		return "redirect:/listaccount";
		
	}
	
	
	
}
