package com.example.bankproject.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	private Long id;
	private Long sender_account;
	private Long receiver_account;
	private Date createdAt;
	private String type;
	private Integer amount;

}
