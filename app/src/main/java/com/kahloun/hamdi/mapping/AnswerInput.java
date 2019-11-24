package com.kahloun.hamdi.mapping;

public class AnswerInput {
	private String answer;
	private int question_id;
	private int user_id;

	public String getAnswer() {
		return answer;
	}

	public AnswerInput(String answer, int question_id, int user_id) {
		this.answer = answer;
		this.question_id = question_id;
		this.user_id = user_id;
	}

	public AnswerInput() {

	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
