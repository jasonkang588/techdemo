package com.kkscom.demo.model.json;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//주요 @ 정리 ===>  https://sjh836.tistory.com/164
//검색어 스프링 jackson annotations
@JsonInclude(Include.NON_NULL)
public class SampleObject {
	private Long id;
	private String email;
	@JsonIgnore
	private String password;
	private String name;
	
	@JsonFormat(shape = Shape.STRING)  // ISO-8601 형식으로 변환
	private LocalDateTime registerDateTime; //2019-09-30T11:07:49
	
	@JsonFormat(pattern = "yyyyMMddHHmmss")  
	private LocalDateTime registerDateTime2;
	
	private Map<String,Object> data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}

	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}

	public LocalDateTime getRegisterDateTime2() {
		return registerDateTime2;
	}

	public void setRegisterDateTime2(LocalDateTime registerDateTime2) {
		this.registerDateTime2 = registerDateTime2;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}
