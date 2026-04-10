package com.example.bankproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bankproject.entity.NoticeEntity;

import jakarta.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>{
	@Modifying(clearAutomatically = true) // 쿼리 실행 후 영속성 컨텍스트 초기화
    @Transactional
	@Query(value = "insert into bank_notice_tbl (nid, title, content, name, CREATED_AT) values (BANK_NOTICE_TBL_SEQ.nextval, :title, :content, '관리자', sysdate)", nativeQuery = true)
	int saveNotice(@Param("title") String title, @Param("content") String content);
	
	@Transactional
	@Query(value = "select * from bank_notice_tbl", nativeQuery = true)
	List<NoticeEntity> getNotices();
	
	@Transactional
	@Query(value = "select * from bank_notice_tbl where nid = :id", nativeQuery = true)
	NoticeEntity getNotice(@Param("id") Long id);
}
