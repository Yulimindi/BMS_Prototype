package com.example.bankproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankproject.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{

}
