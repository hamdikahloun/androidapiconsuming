package com.kahloun.hamdi.model;


public class Question {

	private int question_id;
	private String question;

	public Question() {

	}

	public Question(String question) {
		this.question = question;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
