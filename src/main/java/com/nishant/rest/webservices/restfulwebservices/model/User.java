package com.nishant.rest.webservices.restfulwebservices.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User details")
public class User {
	
	private Integer id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	@ApiModelProperty(notes = "Name should have atleast 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "Birthdate should be in past")
	private Date birthDate;
	
	protected User() {}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
