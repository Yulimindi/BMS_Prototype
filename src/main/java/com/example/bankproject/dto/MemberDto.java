package com.example.bankproject.dto;

import com.example.bankproject.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
	private String id;
	private String username;
	private String password;
	private Role role;
	
	public MemberEntity dtoToEntity() {
		return MemberEntity.builder().id(id).username(username).password(password).role(role).build();
	}
}
