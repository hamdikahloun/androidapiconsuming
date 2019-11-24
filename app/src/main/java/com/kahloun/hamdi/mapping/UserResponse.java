package com.kahloun.hamdi.mapping;

import com.kahloun.hamdi.model.User;

public class UserResponse {
	private int code;
	private String error;
	private User data;

	public UserResponse(int code, String error, User data) {
		this.code = code;
		this.error = error;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}

}
