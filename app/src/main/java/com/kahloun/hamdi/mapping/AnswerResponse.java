package com.kahloun.hamdi.mapping;


import com.kahloun.hamdi.model.Answer;

public class AnswerResponse {
	private int code;
	private String error;
	private Answer data;

	public AnswerResponse(int code, String error, Answer data) {
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

	public Answer getData() {
		return data;
	}

	public void setData(Answer data) {
		this.data = data;
	}

}
