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

import com.entity.CategoryEntity;
import com.entity.UserEntity;
import com.repository.CategoryRepository;
import com.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepo;
    
    @Autowired
	UserRepository userRepo;
    
    @Autowired
    private HttpSession session;

    @GetMapping("addcategory")
    public String addCategory() {
        return "AddCategory";
    }

    @PostMapping("savecategory")
    public String saveCategory(CategoryEntity category) {
    	
    	UUID userId = (UUID) session.getAttribute("userId");
		Optional<UserEntity> optUser = userRepo.findById(userId);
		if(optUser.isPresent()) {
			category.setUsers(optUser.get());
			categoryRepo.save(category);
			
			
	        Optional<CategoryEntity> optCategory = categoryRepo.findById(category.getCategoryId());
	        session.setAttribute("categoryId", category.getCategoryId());

			System.out.println("Category ID from session: " + category.getCategoryId());

			 return "redirect:/listcategory";
			 
		}else {
	        
	        return "redirect:/addcategory?error=userNotFound";
			
		}       
    }

    @GetMapping("/listcategory")
    public String listCategory(Model model) {
        List<CategoryEntity> categoryList = categoryRepo.findAll();
        model.addAttribute("categoryList", categoryList);
        return "ListCategory";
    }

    @GetMapping("/deletecategory")
    public String deleteCategory(@RequestParam Long categoryId) {
        categoryRepo.deleteById(categoryId);
        return "redirect:/listcategory";
    }

    @GetMapping("editcategory")
    public String editaCategory(@RequestParam Long id, Model model) {
        Optional<CategoryEntity> optional = categoryRepo.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/listcategory"; 
        }
        CategoryEntity category = optional.get();
        model.addAttribute("category", category);
        return "EditCategory";
    }

    @PostMapping("updatecategory")
    public String updateCategory(CategoryEntity category ,Model model) { 
    	
    	UUID userId = (UUID) session.getAttribute("userId");
		
		if (userId == null) {
	        model.addAttribute("error", "User not logged in.");
	        return "redirect:/login"; 
	        }
		
		Optional<UserEntity> optUser = userRepo.findById(userId);
		category.setUsers(optUser.get());
        categoryRepo.save(category);
        return "redirect:/listcategory";
    }
}
