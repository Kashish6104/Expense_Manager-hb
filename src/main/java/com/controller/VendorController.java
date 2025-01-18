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

import com.entity.VendorEntity;
import com.repository.VendorRepository;

@Controller

public class VendorController {

	
	@Autowired
	VendorRepository vendorRepo;
	
	
	
	@GetMapping("addvendor")
	public String addVendor() {
		return "AddVendor";
	}
	
//	@GetMapping("listvendor")
//	public String listVendor() {
//		return "ListVendor";
//	}

	
	@PostMapping("savevendor")
	public String saveVendor(VendorEntity vendor) {
		vendorRepo.save(vendor);
		return "redirect:/listvendor";
	}
	
	@GetMapping("listvendor")
	public String listVendor(Model model) {
		List<VendorEntity> vendors = vendorRepo.findAll();
		model.addAttribute("vendors",vendors);
		return "ListVendor";
	}
	
	@GetMapping("deletevendor")
	public String deleteVendor(@RequestParam ("id") UUID vendorId) {
		vendorRepo.deleteById(vendorId);
		return "redirect:/listvendor";
		
	}
	
	@GetMapping("editvendor")
	public String editVendor(@RequestParam UUID id, Model model) {
		Optional <VendorEntity> optional = vendorRepo.findById(id);
		if(optional.isEmpty()) {
			return "redirect:/listvendor";
		}
		VendorEntity vendor = optional.get();
		model.addAttribute("vendor",vendor);
		return "EditVendor";		
	
	}
	

	@PostMapping("updatevendor")
	public String updateVendor(VendorEntity vendor){
		vendorRepo.save(vendor);
		return "redirect:/listvendor";
		
	}
}
