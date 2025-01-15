package com.learn.microservice.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.validation.constraints.Past;

@JsonFilter(value = "IgoreBrithdate")
public class User {

	private long id;
	private String name;
	@Past(message = "Birthday should be past date")
	private LocalDate birthdate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public User(long id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}

}
