package com.example.bankproject.entity;

import org.hibernate.annotations.ColumnDefault;

import com.example.bankproject.dto.AccountDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bank_account_tbl")
public class AccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountNumber;
	
	@Column
	@ColumnDefault("0")
	private Integer balance;
	
	@JoinColumn(name="member_id")
	@ManyToOne
	private MemberEntity member;
	
	@Column
	private String status;
	
	public AccountDto entityToDto() {
		return AccountDto.builder().accountNumber(accountNumber).balance(balance).member_id(this.member.getId()).status(status).build();
	}
}
