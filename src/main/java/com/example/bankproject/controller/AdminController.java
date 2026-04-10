package com.example.bankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bankproject.dto.NoticeDto;
import com.example.bankproject.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	final AdminService as;
	
	@GetMapping("/stateYes")
	public String stateYes(@RequestParam("id") String id) {
		as.stateNo(id);
		return "redirect:/admin/members?state=true";
	}
	
	@GetMapping("/stateNo")
	public String stateNo(@RequestParam("id") String id) {
		as.stateYes(id);
		return "redirect:/admin/members?state=true";
	}
	
	@GetMapping("/members")
	public String members(Model m, @RequestParam(value="state", required=false) String state) {
		if(state != null) {
			m.addAttribute("change", "상태가 변경되었습니다.");
		}
		m.addAttribute("list", as.members());
		return "admin/getMemberPage";
	}
	
	@GetMapping("/oneMemberPage")
	public String oneMemberPage(@RequestParam("id") String id, Model m) {
		m.addAttribute("list", as.oneMember(id));
		m.addAttribute("name", as.getMemberName(id));
		return "admin/oneMemberPage";
	}
	
	@PostMapping("/createNotice")
	public String createNotice(NoticeDto dto) {
		if(as.createNotice(dto)) {
			return "redirect:/";
		}
		return "redirect:/";
	}
	
	
	
}
