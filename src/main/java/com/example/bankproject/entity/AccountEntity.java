package com.example.bankproject.entity;

import org.hibernate.annotations.ColumnDefault;

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
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
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
}
