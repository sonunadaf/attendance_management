package com.proj.syncbyte.controller.register.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class RequestDTO {

	private String name;
	private String empCode;
	private String password;
	private Date dateOfBirth;
	private MultipartFile file;

	public RequestDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "RequestDTO [name=" + name + ", empCode=" + empCode + ", password=" + password + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

}
