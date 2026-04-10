package com.example.bankproject.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.bankproject.dto.TransactionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name="bank_transaction_tbl")
@EntityListeners(AuditingEntityListener.class)
public class TransactionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@JoinColumn(name="sender_account")
	@ManyToOne
	private AccountEntity sender_account;
	
	@JoinColumn(name="receiver_account")
	@ManyToOne
	private AccountEntity receiver_account;
	
	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime createdAt;
	
	@Column
	private String type;
	
	@Column
	private Integer amount;
	
	public TransactionDto entityToDto() {
		return TransactionDto.builder().id(id).sender_account(this.sender_account.getAccountNumber()).receiver_account(this.receiver_account.getAccountNumber()).createdAt(createdAt).type(type).amount(amount).build();
	}
}
