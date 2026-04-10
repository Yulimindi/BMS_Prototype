package com.example.bankproject.controller;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bankproject.dto.MemberDto;
import com.example.bankproject.dto.Role;
import com.example.bankproject.jwt.JwtUtil;
import com.example.bankproject.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	final BCryptPasswordEncoder encoder;
	final MemberService ms;
	final JwtUtil util;
	
	@PostMapping("/regist")
	public String regist(MemberDto m) {
		m.setPassword(encoder.encode(m.getPassword()));
		ms.regist(m);
		return "redirect:/?regist=ok";
	}
	
	@GetMapping("/checkId")
	public @ResponseBody String checkId(@RequestParam("id") String id) {
		if(ms.checkId(id)) {
			return "ok";
		}
		return "alreadyused";
	}
	
	@PostMapping("/login")
	public String login(MemberDto m, HttpServletResponse response) {
		Map<Boolean, MemberDto> map = ms.login(m);
		if(map.containsKey(true)) {
			
			String token = util.generateToken(map.get(true).getId(), map.get(true).getRole(), map.get(true).getUsername());
			Cookie cookie = new Cookie("token", token);
			cookie.setPath("/");
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
			Role role = map.get(true).getRole();
			System.out.println(role);
			if(role.name().equals("ROLE_MEMBER")) {
				return "redirect:/member/mainPage";
			} else {
				return "redirect:/admin/adminPage";
			}
			
		} else {
			return "redirect:/loginPage?login=false";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		return "redirect:/?logout=true";
	}
}
