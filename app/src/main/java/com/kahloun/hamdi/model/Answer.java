package com.kahloun.hamdi.model;

import java.util.Date;

public class Answer {

	private int answer_id;
	private String answer;
	private Question question;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	private Date creation_date = new Date();
	public Answer() {

	}

	public Answer(String answer, Question question,User user) {
		this.answer = answer;
		this.question = question;
		this.user = user;
	}

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
