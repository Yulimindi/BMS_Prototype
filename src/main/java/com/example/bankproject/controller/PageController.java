package com.example.bankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	@GetMapping("/")
	public String index(@RequestParam(value="regist", required=false) String regist, Model m) {
		if(regist != null) {
			m.addAttribute("regist", "가입이 완료되었습니다.");
		}
		return "index";
	}
	
	@GetMapping("/registPage")
	public String registPage() {
		return "registPage";
	}
	
	@GetMapping("/loginPage")
	public String loginPage(@RequestParam(value="login", required=false) String login, Model m) {
		if(login != null) {
			m.addAttribute("login", "아이디 또는 비밀번호가 틀렸습니다.");
		}
		return "loginPage";
	}
	
	@GetMapping("/member/mainPage")
	public String mainPage() {
		return "/member/mainPage";
	}
	
	@GetMapping("/member/depositPage")
	public String depositPage() {
		return "member/depositPage";
	}
	
	@GetMapping("/member/transferPage")
	public String transferPage() {
		return "member/transferPage";
	}
	
	@GetMapping("/member/getAccountPage")
	public String getAccountPage() {
		return "member/getAccountPage";
	}
	
	@GetMapping("/admin/adminPage")
	public String adminPage() {
		return "admin/adminPage";
	}
	
	@GetMapping("/admin/getMemberPage")
	public String getMemberPage() {
		return "admin/getMemberPage";
	}
	
	@GetMapping("/admin/oneMemberPage")
	public String oneMemberPage() {
		return "admin/oneMemberPage";
	}
}
