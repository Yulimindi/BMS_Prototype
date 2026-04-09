package com.example.bankproject.service;

import org.springframework.stereotype.Service;

import com.example.bankproject.repository.AccountRepository;
import com.example.bankproject.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	AccountRepository ar;
	MemberRepository mr;
	
	public String state() {
		return "";
	}
	
	public String members() {
		return "";
	}
}
