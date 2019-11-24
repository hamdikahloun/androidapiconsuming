package com.kahloun.hamdi.mapping;

import com.kahloun.hamdi.model.Question;

public class QuestionResponse {
	private int code;
	private String error;
	private Question data;

	public QuestionResponse(int code, String error, Question data) {
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

	public Question getData() {
		return data;
	}

	public void setData(Question data) {
		this.data = data;
	}

}
