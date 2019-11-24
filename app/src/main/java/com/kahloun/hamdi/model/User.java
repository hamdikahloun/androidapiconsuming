package com.kahloun.hamdi.model;

import java.util.Date;

public class User {

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id;
	private String email;
	private String password;
	private Boolean confirmed;
	private String name;
	private Date creation_date = new Date();

	public User() {

	}

	public User(String email,String password,String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the creation_date
	 */
	public Date getCreation_date() {
		return creation_date;
	}

	/**
	 * @param creation_date the creation_date to set
	 */
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	/**
	 * @return the confirmed
	 */
	public Boolean getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed the confirmed to set
	 */
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

    public int getUser_id() {
        return user_id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
