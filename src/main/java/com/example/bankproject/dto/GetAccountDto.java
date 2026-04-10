package com.example.bankproject.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountDto {
	Integer balance;
	List<TransactionDto> lists;
}
