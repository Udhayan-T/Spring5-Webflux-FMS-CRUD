package com.fmscapstone.dto;

import java.util.List;

import com.fmscapstone.model.Answers;


public class AnswerRequestDTO {
	
	private List<Answers> answerList;
	
	private Long feedbackId;

	public List<Answers> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answers> answerList) {
		this.answerList = answerList;
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	

}
