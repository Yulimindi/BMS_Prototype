package com.example.bankproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	private Long accountNumber;
	private Integer balance;
	private String member_id;
	private String status;
}
