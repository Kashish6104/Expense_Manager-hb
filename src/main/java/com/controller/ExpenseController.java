package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.entity.ExpensesEntity;
import com.repository.ExpenseRepository;
import com.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ExpenseController {

	@Autowired
	ExpenseRepository expenseRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("addexpense")
	public String addExpense() {
		return "AddExpense";
	}

	
	@PostMapping("saveexpense")
	public String saveExpense(ExpensesEntity expense) {
		expenseRepo.save(expense);
		return "redirect:/listexpense";
	}
	
	@GetMapping("listexpense")
	public String listExpense(Model model) {
		List<ExpensesEntity> expense = expenseRepo.findAll();
		model.addAttribute("expense",expense);
		return "ListExpense";
	}
	
	
	@GetMapping("deleteexpense")
	public String deleteExpense(@RequestParam ("id") Long expenseId) {
		expenseRepo.deleteById(expenseId);
		return "redirect:/listaccount";
	}
	
	@GetMapping("editexpense")
	public String editExpense(@RequestParam Long id,Model model) {
		Optional<ExpensesEntity> optional = expenseRepo.findById(id);
		if(optional.isEmpty()) {
			return "redirect:/listexpense";
		}
		ExpensesEntity expense = optional.get();
		model.addAttribute("expense",expense);
		return "EditExpense";
	}
	
	@PostMapping("updateexpense")
	public String updateExpense(ExpensesEntity expense){
		expenseRepo.save(expense);
		return "redirect:/listexpense";
		
	}
	
	
	
}
