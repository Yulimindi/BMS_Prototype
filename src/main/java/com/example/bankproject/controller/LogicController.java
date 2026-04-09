package com.example.bankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bankproject.service.LogicService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class LogicController {
	
	final LogicService ls;
	
	@Transactional
	@PostMapping("/deposit")
	public String deposit(@RequestParam("amount") Integer amount, @CookieValue(name="token") String token) {
		if(ls.deposit(amount, token) == true) {
			return "redirect:/member/mainPage?deposit=success";
		}
		return "redirect:/member/mainPage?deposit=success";
	}
	
	// 송금
	@Transactional
	@PutMapping("/transfer")
	public String transfer(@RequestParam("amount") Integer amount, @CookieValue(name="token") String token, @RequestParam("receiver") Long receiver) {
		ls.transfer(amount, token, receiver);
		return "";
	}
	
	// 예금주 확인
	@GetMapping("/checkReceiver")
	public @ResponseBody String checkReceiver(@RequestParam("receiver") Long receiver, @CookieValue(name="token") String token) {
		return ls.checkReceiver(receiver, token);
	}
	
	// 내 계좌 잔액 및 최근 거래 내역 5개 조회
	@Transactional
	@GetMapping("/account")
	public String account() {
		return "";
	}
}
