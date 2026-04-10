package com.example.bankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bankproject.service.AdminService;
import com.example.bankproject.service.LogicService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	
	final LogicService ls;
	final AdminService as;
	
	@GetMapping("/")
	public String index(@RequestParam(value="regist", required=false) String regist, Model m, @RequestParam(value="logout", required=false) String logout) {
		if(regist != null) {
			m.addAttribute("regist", "가입이 완료되었습니다.");
		}
		
		if(logout != null) {
			m.addAttribute("logout", "로그아웃 되었습니다.");
		}
		m.addAttribute("list", as.getNotice());
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
	public String mainPage(@RequestParam(value="deposit", required=false) String deposit, @RequestParam(value="transfer", required=false) String transfer, @RequestParam(value="frozen", required=false) String frozen, Model m) {
		if(deposit != null) {
			if(deposit.equals("success")) {
				m.addAttribute("deposit", "입금이 완료되었습니다.");
			} else if(deposit.equals("fail")){
				m.addAttribute("deposit", "입금이 실패했습니다.");
			} else if(deposit.equals("frozen")) {
				m.addAttribute("deposit", "해당 계좌는 거래가 제한되어 있습니다.");
			}
		}
		if(transfer != null) {
			if(transfer.equals("success")) {
				m.addAttribute("transfer", "송금이 완료되었습니다.");
			} else if(transfer.equals("fail")) {
				m.addAttribute("transfer", "잔액이 부족합니다.");
			} else if(transfer.equals("frozen")) {
				m.addAttribute("transfer", "해당 계좌는 거래가 제한되어 있습니다.");
			}
		}
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
	public String getAccountPage(@CookieValue(name="token") String token, Model m) {
		m.addAttribute("list", ls.account(token));
		return "member/getAccountPage";
	}
	
	@GetMapping("/admin/adminPage")
	public String adminPage() {
		return "admin/adminPage";
	}
	
	@GetMapping("/admin/makeNotice")
	public String makeNotice() {
		return "admin/makeNoticePage";
	}
	
	@GetMapping("/notice")
	public String getOneNotice(@RequestParam("id") Long id, Model m) {
		m.addAttribute("notice", as.getOneNotice(id));
		return "noticePage";
	}
	
}
