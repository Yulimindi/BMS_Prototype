package com.example.bankproject.dto;

import java.time.LocalDateTime;

import com.example.bankproject.entity.NoticeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoticeDto {
	Long nid;
	String title;
	String content;
	String name;
	LocalDateTime createdAt;
	
	public NoticeEntity dtoToEntity() {
		return NoticeEntity.builder().nid(nid).title(title).content(content).name(name).createdAt(createdAt).build();
	}
}
