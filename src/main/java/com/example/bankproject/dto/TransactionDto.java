package com.example.bankproject.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
	private Long id;
	private Long sender_account;
	private Long receiver_account;
	private LocalDateTime createdAt;
	private String type;
	private Integer amount;

}
