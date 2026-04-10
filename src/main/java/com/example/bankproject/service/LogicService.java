package com.example.bankproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bankproject.dto.GetAccountDto;
import com.example.bankproject.dto.TransactionDto;
import com.example.bankproject.entity.TransactionEntity;
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
	
	public boolean checkDepositState(String token) {
		String id = util.getId(token);
		Long accountId = ar.findAccountId(id);
		String result = ar.getState(accountId);
		if(result.equals("YES")) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean checkTransferState(String token, Long receiver) {
		String id = util.getId(token);
		Long accountId = ar.findAccountId(id);
		String result = ar.getState(accountId);
		if(result.equals("YES")) {
			if(ar.getState(receiver).equals("YES")) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
	
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
	public boolean transfer(Integer amount, String token, Long receiver) {
		String id = util.getId(token);
		Long accountId = ar.findAccountId(id);
		Integer balance = ar.getBalance(accountId);
		if(balance - amount < 0) {
			return false;
		}
		balance = balance - amount;
		ar.deposit(accountId, balance);
		Integer receiverBalance = ar.getBalance(receiver);
		ar.deposit(receiver, amount + receiverBalance);
		tr.saveTransfer(accountId, receiver, amount);
		return true;
	}
	
	public String checkReceiver(@RequestParam("receiver") Long receiver, String token) {
		String id = util.getId(token);
		Long accountId = ar.findAccountId(id);
		if(ar.existsById(receiver)) {
			if(ar.findById(receiver).get().getAccountNumber().equals(accountId)) {
				return "same";
			}
			return ar.findById(receiver).get().getMember().getUsername();
		} else {
			return "none";
		}
		
	}

	public GetAccountDto account(String token) {
		String id = util.getId(token);
		Long accountId = ar.findAccountId(id);
		Integer balance = ar.getBalance(accountId);
		List<TransactionEntity> entites = tr.getTransaction(accountId);
		List<TransactionDto> dto = entites.stream().map(entity -> entity.entityToDto()).toList();
		
		GetAccountDto dtos = new GetAccountDto(balance, dto);
		return dtos;
	}
	
}
