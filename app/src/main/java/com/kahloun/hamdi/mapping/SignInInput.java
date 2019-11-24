package com.kahloun.hamdi.mapping;

public class SignInInput {
	private String email;
	private String password;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	public SignInInput(String email, String password) {
		this.email = email;
		this.password = password;
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

}
