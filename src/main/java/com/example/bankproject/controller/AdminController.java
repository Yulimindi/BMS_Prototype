package com.example.bankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bankproject.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	AdminService as;
	
	@PutMapping("/state")
	public String state() {
		return "";
	}
	
	@GetMapping("/members")
	public String members() {
		return "";
	}
}
