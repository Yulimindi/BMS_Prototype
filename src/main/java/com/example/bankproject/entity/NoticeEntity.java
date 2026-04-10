package com.example.bankproject.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.example.bankproject.dto.NoticeDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bank_notice_tbl")
public class NoticeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long nid;
	
	@Column
	String title;
	
	@Column
	String content;
	
	@Column
	String name;
	
	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime createdAt;
	
	public NoticeDto entityToDto() {
		return NoticeDto.builder().nid(nid).title(title).content(content).name(name).createdAt(createdAt).build();
	}
}
