package com.jerry.jerry.application.dto;

import java.math.BigDecimal;
import com.jerry.jerry.core.*;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;

public class AccountDTO implements Serializable {

	private Long id;

	private BigDecimal balance;

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void domain2Dto(Account account) {
		this.setId(account.getId());
		this.setBalance(account.getBalance());
	}

	public void dto2Domain(Account account) {
		account.setBalance(this.getBalance());
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
		AccountDTO other = (AccountDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}