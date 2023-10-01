package com.ishaque.coaching.microservices.model;

import com.ishaque.coaching.microservices.validate.FieldMatch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class Candidate {

	@NotBlank
	private String name;
	private String course;
	private String medium;
	private String degreeName;
	private String address;
	private String mobile;
	@Email
	private String email;
	private String password;
	private String confirmPassword;
	private String degreeCompleteYear;
	public String getTechDetais() {
		return techDetais;
	}

	public void setTechDetais(String techDetais) {
		this.techDetais = techDetais;
	}

	private String techDetais;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDegreeCompleteYear() {
		return degreeCompleteYear;
	}

	public void setDegreeCompleteYear(String degreeCompleteYear) {
		this.degreeCompleteYear = degreeCompleteYear;
	}

	
	
}
