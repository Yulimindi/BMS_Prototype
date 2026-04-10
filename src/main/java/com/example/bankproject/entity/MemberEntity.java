package com.example.bankproject.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.bankproject.dto.MemberDto;
import com.example.bankproject.dto.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bank_member_tbl")
public class MemberEntity {

	@Id
	private String id;
	
	// mappedBy = "member"는 AccountEntity에 있는 'private MemberEntity member' 필드 이름을 적음
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts = new ArrayList<>();
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public MemberDto entityToDto() {
		return MemberDto.builder().id(id).username(username).password(password).role(role).build(); 
	}
}
