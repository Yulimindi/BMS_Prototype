package com.example.bankproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bankproject.jwt.JwtUtil;
import com.example.bankproject.repository.AccountRepository;
import com.example.bankproject.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogicService {
	
	final TransactionRepository tr;
	final AccountRepository ar;
	final JwtUtil util;
	
	@Transactional
	public boolean deposit(Integer amount, String token) {
		// token -> id를 가져옴 = admin => 이걸로 amount id를 찾아와야함 그리고 그 id에다가 넣어줘야함
		String id = util.getId(token);
		Long accountId = ar.findAccountId(id);
		Integer balance = null;
		if(accountId != null) {
			balance = ar.getBalance(accountId) + amount;
			ar.deposit(accountId , balance);
			tr.saveDeposit(accountId, accountId, amount);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public String transfer(Integer amount, String token, Long receiver) {
		
		return "";
	}
	
	public String checkReceiver(@RequestParam("receiver") Long receiver, String token) {
		String id = util.getId(token);
		Long accountId = ar.findAccountId(id);
		if(ar.existsById(receiver)) {
			System.out.println("내 아이디 : " + accountId);
			System.out.println("송금할 아이디 : " + ar.findById(receiver).get().getAccountNumber());
			if(ar.findById(receiver).get().getAccountNumber().equals(accountId)) {
				return "same";
			}
			return ar.findById(receiver).get().getMember().getUsername();
		} else {
			return "none";
		}
		
	}

	public String account() {
		return "";
	}
}
