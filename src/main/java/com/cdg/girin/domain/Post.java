package com.cdg.girin.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Entity
public class Post {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "제목을 입력해주세요.")
	@Size(max = 255)
	@Column(nullable = false)
	private String title;
	
	@Size(max = 10)
	private String subTitle;
	
	@NotBlank(message = "내용을 입력해주세요.")
	@Size(max = 100000000)
	@Column(length = 100000000 , nullable = false)
	private String content;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime regDate;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime modifyDate;
}
