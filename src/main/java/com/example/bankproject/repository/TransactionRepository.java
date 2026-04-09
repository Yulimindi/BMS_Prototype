package com.example.bankproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bankproject.entity.TransactionEntity;

import jakarta.transaction.Transactional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	
	@Modifying(clearAutomatically = true) // 쿼리 실행 후 영속성 컨텍스트 초기화
    @Transactional
	@Query(value = "insert into bank_transaction_tbl (id, sender_account, receiver_account, type, amount) values (BANK_TRANSACTION_TBL_SEQ.nextval, :sender, :receiver, 'deposit', :amount)", nativeQuery = true)
	int saveDeposit(@Param("sender") Long sender, @Param("receiver") Long receiver, @Param("amount") Integer amount);
}
