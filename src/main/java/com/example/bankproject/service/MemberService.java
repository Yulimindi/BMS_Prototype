package com.example.bankproject.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bankproject.BankprojectApplication;
import com.example.bankproject.dto.MemberDto;
import com.example.bankproject.entity.MemberEntity;
import com.example.bankproject.jwt.JwtUtil;
import com.example.bankproject.repository.AccountRepository;
import com.example.bankproject.repository.MemberRepository;

import jakarta.servlet.http.Cookie;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MemberService {

	final MemberRepository mr;
	final AccountRepository ar;
	final BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public boolean regist(MemberDto m) {
		MemberEntity entity = mr.save(m.dtoToEntity());
		ar.save(m.getId());
		return true;
	}
	
	public boolean checkId(String id) {
		Optional<MemberEntity> op = mr.findById(id);
		if(!op.isPresent()) {
			System.out.println("가능");
			return true;
		}
		System.out.println("불가능");
		return false;
	}
	
	public Map<Boolean, MemberDto> login(MemberDto m) {
		Optional<MemberEntity> op = mr.findById(m.getId());
		Map<Boolean, MemberDto> map = new HashMap<>();
		if(op.isPresent()) {
			if(m.getPassword() != null && passwordEncoder.matches(m.getPassword(), op.get().getPassword())) {
				map.put(true, op.get().entityToDto());
				return map;
			} else {
				map.put(false, m);
				return map;
			}
		} else {
			map.put(false, m);
			return map;
		}
		
	}
}
