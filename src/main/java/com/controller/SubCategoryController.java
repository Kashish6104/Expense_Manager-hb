package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.SubCategoryEntity;
import com.repository.SubCategoryRepository;

@Controller
public class SubCategoryController {
	
	@Autowired
	SubCategoryRepository subcategoryRepo;

	@GetMapping("addsubcategory")
	public String addSubCategory() {
		return "AddSubCategory";
	}
	
	@PostMapping("savesubcategory")
	public String saveSubCategory(SubCategoryEntity subcategory) {
		subcategoryRepo.save(subcategory);
		return "redirect:/listsubcategory";
	}
	
	@GetMapping("/listsubcategory")
	public String listSubCategory(Model model) {
		List<SubCategoryEntity> subcategoryList = subcategoryRepo.findAll();
		model.addAttribute("subcategoryList", subcategoryList);
		return "ListSubCategory";
	}
	
	@GetMapping("/deletesubcategory")
	public String deleteSubCategory(@RequestParam Long subcategoryId) {  // Changed to Long for consistency
		subcategoryRepo.deleteById(subcategoryId);
		return "redirect:/listsubcategory";
	}
	
	@GetMapping("editsubcategory")
	public String editSubCategory(@RequestParam Long id, Model model) { 
		Optional<SubCategoryEntity> optional = subcategoryRepo.findById(id);
		if (optional.isEmpty()) {
			return "redirect:/listsubcategory"; 
		}
		SubCategoryEntity subcategory = optional.get();
		model.addAttribute("subcategory", subcategory);
		return "EditSubCategory";
	}

	@PostMapping("updatesubcategory")
	public String updateSubCategory(SubCategoryEntity subcategory) { 
		subcategoryRepo.save(subcategory);
		return "redirect:/listsubcategory";
	}
}
