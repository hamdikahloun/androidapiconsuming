package com.kahloun.hamdi.mapping;

public class QuestionIDInput {
	private int question_id;
	private int user_id;

	public QuestionIDInput(int question_id, int user_id) {
		this.question_id = question_id;
		this.user_id = user_id;
	}

	/**
	 * @return the question_id
	 */
	public int getQuestion_id() {
		return question_id;
	}

	/**
	 * @param question_id the question_id to set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
