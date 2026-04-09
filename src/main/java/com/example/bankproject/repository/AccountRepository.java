package com.example.bankproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bankproject.entity.AccountEntity;
import com.example.bankproject.entity.MemberEntity;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
	
	@Modifying(clearAutomatically = true) // 쿼리 실행 후 영속성 컨텍스트 초기화
    @Transactional
	@Query(value = "insert into bank_account_tbl (account_number, status, member_id) values (bank_account_tbl_seq.nextval, 'YES' , :id)", nativeQuery = true)
	int save(@Param("id") String id);

	@Modifying(clearAutomatically = true)
    @Transactional
	@Query(value = "update bank_account_tbl set balance = :balance where ACCOUNT_NUMBER = :id", nativeQuery = true)
	int deposit(@Param("id") Long id, @Param("balance") Integer balance);
	
    @Transactional
	@Query(value = "select ACCOUNT_NUMBER from bank_account_tbl where MEMBER_ID = :id", nativeQuery = true)
	Long findAccountId(@Param("id") String id);
	
    @Transactional
	@Query(value = "select balance from bank_account_tbl where ACCOUNT_NUMBER = :accountId", nativeQuery = true)
	int getBalance(@Param("accountId") Long accountId);
}
