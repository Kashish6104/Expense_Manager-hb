package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.entity.IncomeEntity;
import com.repository.IncomeRepository;
import com.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IncomeController {

	@Autowired
	IncomeRepository incomeRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("addincome")
	public String addIncome() {
		return "AddIncome";
	}

	
	@PostMapping("saveincome")
	public String saveExpense(IncomeEntity income) {
		incomeRepo.save(income);
		return "redirect:/listincome";
	}
	
	@GetMapping("listincome")
	public String listIncome(Model model) {
		List<IncomeEntity> income = incomeRepo.findAll();
		model.addAttribute("income",income);
		return "ListIncome";
	}
	
	
	@GetMapping("deleteincome")
	public String deleteIncome(@RequestParam ("id") Long incomeId) {
		incomeRepo.deleteById(incomeId);
		return "redirect:/listincome";
	}
	
	@GetMapping("editincome")
	public String editIncome(@RequestParam Long id,Model model) {
		Optional<IncomeEntity> optional = incomeRepo.findById(id);
		if(optional.isEmpty()) {
			return "redirect:/listincome";
		}
		IncomeEntity income = optional.get();
		model.addAttribute("income",income);
		return "EditIncome";
	}
	
	@PostMapping("updateincome")
	public String updateIncome(IncomeEntity income){
		incomeRepo.save(income);
		return "redirect:/listincome";
		
	}
	
	
	
}
