package com.example.bankproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bankproject.dto.AccountDto;
import com.example.bankproject.dto.MemberDto;
import com.example.bankproject.dto.NoticeDto;
import com.example.bankproject.entity.MemberEntity;
import com.example.bankproject.repository.AccountRepository;
import com.example.bankproject.repository.MemberRepository;
import com.example.bankproject.repository.NoticeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	final AccountRepository ar;
	final MemberRepository mr;
	final NoticeRepository nr;
	
	@Transactional
	public boolean stateYes(String id) { // YES로 변경
		ar.changeYes(ar.findAccountId(id));
		return true;
	}
	
	@Transactional
	public boolean stateNo(String id) { // NO로 변경
		ar.changeNo(ar.findAccountId(id));
		return true;
	}
	
	@Transactional
	public AccountDto oneMember(String id) {
		return ar.getAccount(ar.findAccountId(id)).entityToDto();
	}
	
	@Transactional
	public String getMemberName(String id) {
		return mr.getName(id);
	}
	
	@Transactional
	public List<MemberDto> members() {
		List<MemberEntity> entites = mr.findAll();
		List<MemberDto> dto = entites.stream().map(entity -> entity.entityToDto()).toList();
		return dto;
	}
	
	@Transactional
	public boolean createNotice(NoticeDto dto) {
		nr.saveNotice(dto.getTitle(), dto.getContent());
		return true;
	}
	
	@Transactional
	public List<NoticeDto> getNotice() {
		return nr.getNotices().stream().map(notice -> notice.entityToDto()).toList();
	}
	
	@Transactional
	public NoticeDto getOneNotice(Long id) {
		return nr.getNotice(id).entityToDto();
	}
	
}
