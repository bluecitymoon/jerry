package com.jerry.jerry.core;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.dayatang.domain.BaseLegacyEntity;
import java.io.Serializable;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "Users")
public class Users extends BaseLegacyEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 主键
	 * 
	 **/

	@Id
	@Column(name = "id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private int age;

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((this.id == 0) ? 0 : (int) this.id);

		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());

		result = prime * result
				+ ((this.firstName == null) ? 0 : this.firstName.hashCode());

		result = prime * result
				+ ((this.lastName == null) ? 0 : this.lastName.hashCode());

		result = prime * result
				+ ((this.email == null) ? 0 : this.email.hashCode());

		result = prime * result + ((this.age == 0) ? 0 : (int) this.age);

		return result;
	}

	public boolean equals(Object other) {
		// TODO your must todo it yourself
		return false;
	}

	public String toString() {
		// TODO your must todo it yourself
		return null;
	}

	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existed(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return false;
	}

}