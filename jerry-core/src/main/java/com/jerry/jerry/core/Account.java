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
import com.dayatang.domain.AbstractEntity;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "ACCOUNT")
public class Account extends AbstractEntity {

	@Column(name = "balance")
	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == 0) ? 0 : getId().hashCode());

		result = prime * result
				+ ((this.balance == null) ? 0 : this.balance.hashCode());

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
}