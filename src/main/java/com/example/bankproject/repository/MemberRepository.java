package com.example.bankproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bankproject.entity.MemberEntity;

import jakarta.transaction.Transactional;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{
	@Transactional
	@Query(value = "select username from bank_member_tbl where id = :id", nativeQuery = true)
	String getName(@Param("id") String id);
}
