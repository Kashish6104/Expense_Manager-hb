package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.CategoryEntity;
import com.repository.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepo;

    @GetMapping("addcategory")
    public String addCategory() {
        return "AddCategory";
    }

    @PostMapping("savecategory")
    public String saveCategory(CategoryEntity category) {
        categoryRepo.save(category);
        return "redirect:/listcategory";
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
    public String updateCategory(CategoryEntity category) { 
        categoryRepo.save(category);
        return "redirect:/listcategory";
    }
}
