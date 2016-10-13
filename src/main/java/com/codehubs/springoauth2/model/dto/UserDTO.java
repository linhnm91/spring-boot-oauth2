package com.codehubs.springoauth2.model.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private String accountId;
	private String username;
	private String fistName;
	private String lastName;
	private String phone;

	public UserDTO() {
		super();
	}

	public UserDTO(String accountId, String username, String fistName, String lastName, String phone) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.fistName = fistName;
		this.lastName = lastName;
		this.phone = phone;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
