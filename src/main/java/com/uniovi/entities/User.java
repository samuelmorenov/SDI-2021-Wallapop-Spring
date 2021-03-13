package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;

	private String role;

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;

	private double money;

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private Set<Offer> created;

	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
	private Set<Offer> bought;

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.money = 100;
	}

	public User() {
		this.money = 100;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Set<Offer> getCreated() {
		return created;
	}

	public void setCreated(Set<Offer> created) {
		this.created = created;
	}

	public Set<Offer> getBought() {
		return bought;
	}

	public void setBought(Set<Offer> bought) {
		this.bought = bought;
	}

}