package com.example.bankproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bankproject.entity.TransactionEntity;

import jakarta.transaction.Transactional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	
	@Modifying(clearAutomatically = true) // 쿼리 실행 후 영속성 컨텍스트 초기화
    @Transactional
	@Query(value = "insert into bank_transaction_tbl (id, sender_account, receiver_account, type, amount, created_At) values (BANK_TRANSACTION_TBL_SEQ.nextval, :sender, :receiver, 'deposit', :amount, sysdate)", nativeQuery = true)
	int saveDeposit(@Param("sender") Long sender, @Param("receiver") Long receiver, @Param("amount") Integer amount);
	
	@Modifying(clearAutomatically = true) // 쿼리 실행 후 영속성 컨텍스트 초기화
    @Transactional
	@Query(value = "insert into bank_transaction_tbl (id, sender_account, receiver_account, type, amount, created_At) values (BANK_TRANSACTION_TBL_SEQ.nextval, :sender, :receiver, 'transfer', :amount, sysdate)", nativeQuery = true)
	int saveTransfer(@Param("sender") Long sender, @Param("receiver") Long receiver, @Param("amount") Integer amount);
	
    @Transactional
	@Query(value = "select * from bank_transaction_tbl where sender_account = :sender_account or receiver_account = :sender_account order by created_at desc offset 0 rows fetch next 5 rows only", nativeQuery = true)
	List<TransactionEntity> getTransaction(@Param("sender_account") Long sender_account);
}
