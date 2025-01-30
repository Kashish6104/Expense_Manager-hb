package com.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.AccountEntity;
import com.entity.CategoryEntity;
import com.entity.SubCategoryEntity;
import com.entity.UserEntity;
import com.repository.CategoryRepository;
import com.repository.SubCategoryRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SubCategoryController {
	
	@Autowired
	SubCategoryRepository subcategoryRepo;
	
	@Autowired
    CategoryRepository categoryRepo;
	
	@Autowired
	private HttpSession session;

	@GetMapping("addsubcategory")
	public String addSubCategory() {
		return "AddSubCategory";
	}
	

	
	
	@PostMapping("savesubcategory")
	public String saveSubCategory(SubCategoryEntity subcategory) {
	    // Check if the categoryId exists in the session
	    Long categoryId = (Long) session.getAttribute("categoryId");

	    if (categoryId == null) {
	        System.out.println("categoryId not found");
	        return "redirect:/addsubcategory?error=categoryNotFound"; // Handle error case
	    }

	    // Retrieve the category from the repository
	    Optional<CategoryEntity> optCategory = categoryRepo.findById(categoryId);
	    if (optCategory.isPresent()) {
	        // Set the category to the subcategory
	    	CategoryEntity sessionCategoryId = optCategory.get();
		    System.out.println(sessionCategoryId.getCategoryId());

	        subcategory.setCategory(sessionCategoryId);
	        subcategoryRepo.save(subcategory);
	        return "redirect:/listsubcategory";  // Successful save
	    } else {
	        return "redirect:/addsubcategory?error=categoryNotFound";  // Handle error case
	    }
	}


	
	@GetMapping("/listsubcategory")
	public String listSubCategory(Model model) {
		List<SubCategoryEntity> subcategoryList = subcategoryRepo.findAll();
		model.addAttribute("subcategoryList", subcategoryList);
		return "ListSubCategory";
	}
	
	@GetMapping("/deletesubcategory")
	public String deleteSubCategory(@RequestParam Long id) {  
		subcategoryRepo.deleteById(id);
		return "redirect:/listsubcategory";
	}
	
	@GetMapping("editsubcategory")
	public String editSubCategory(@RequestParam Long id, Model model) { 
		Optional<SubCategoryEntity> optional = subcategoryRepo.findById(id);
		if (optional.isEmpty()) {
			return "redirect:/listsubcategory"; // Category not found
		}
		SubCategoryEntity subcategory = optional.get();
		model.addAttribute("subcategory", subcategory);
		return "EditSubCategory";
	}


	
	
	@PostMapping("updatesubcategory")
	public String updateSubCategory(@RequestParam Long id, SubCategoryEntity updatedSubcategory, Model model) {
	    Optional<SubCategoryEntity> optionalSubCategory = subcategoryRepo.findById(id);

	    if (optionalSubCategory.isEmpty()) {
	        model.addAttribute("error", "Subcategory not found");
	        return "redirect:/listsubcategory";
	    }

	    SubCategoryEntity existingSubcategory = optionalSubCategory.get();

	    // Fetch the category from session
	    Long categoryId = (Long) session.getAttribute("categoryId");
	    if (categoryId == null) {
	        model.addAttribute("error", "CategoryId not found");
	        return "redirect:/addsubcategory"; 
	    }

	    Optional<CategoryEntity> optCategory = categoryRepo.findById(categoryId);
	    if (optCategory.isEmpty()) {
	        model.addAttribute("error", "Invalid CategoryId");
	        return "redirect:/addsubcategory"; 
	    }

	    // Update subcategory fields
	    existingSubcategory.setTitle(updatedSubcategory.getTitle());  // Update required fields
	    existingSubcategory.setCategory(optCategory.get()); // Update category

	    // Save updated subcategory
	    subcategoryRepo.save(existingSubcategory);
	    
	    return "redirect:/listsubcategory";
	}

}