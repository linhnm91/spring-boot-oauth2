package com.codehubs.springoauth2.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails, Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	@Column(name="acount_id", length=10)
	private String accountId;
	@NotEmpty
	@Column(name="user_name", length=255, unique=true)
	private String username;
	
	@Column(name="first_name", length=255)
	private String fistName;
	
	@Column(name="last_name", length=255)
	private String lastName;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@JsonIgnore
	@Column(name="salt")
	private String salt;
	
	private String phone;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at" )
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
	private Integer creator;
	
	@JsonIgnore
	@ManyToMany(fetch =FetchType.LAZY)
	@JoinTable(name="user_role", joinColumns= {@JoinColumn(name="user_id")}, inverseJoinColumns = {@JoinColumn(name="role_id")})
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		super();
	}

	public User(Integer id, String accountId, String username, String fistName, String lastName, String password,
			String salt, String phone, Date createdAt, Date updatedAt, Integer creator, Set<Role> roles) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.username = username;
		this.fistName = fistName;
		this.lastName = lastName;
		this.password = password;
		this.salt = salt;
		this.phone = phone;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.creator = creator;
		this.roles = roles;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName(){
		return this.fistName + " " + this.lastName;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
