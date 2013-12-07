package com.jerry.jerry.application.dto;

import com.jerry.jerry.core.*;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;

public class UsersDTO implements Serializable {

	private Long id;

	private String lastName;

	private String email;

	private Integer age;

	private String name;

	private String firstName;

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setId(Serializable id) {
		this.id = (Long) id;
	}

	public Long getId() {
		return id;
	}

	public void domain2Dto(Users users) {
		this.setId(users.getId());
		this.setLastName(users.getLastName());
		this.setEmail(users.getEmail());
		this.setAge(users.getAge());
		this.setName(users.getName());
		this.setFirstName(users.getFirstName());
	}

	public void dto2Domain(Users users) {
		users.setLastName(this.getLastName());
		users.setEmail(this.getEmail());
		users.setAge(this.getAge());
		users.setName(this.getName());
		users.setFirstName(this.getFirstName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersDTO other = (UsersDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}