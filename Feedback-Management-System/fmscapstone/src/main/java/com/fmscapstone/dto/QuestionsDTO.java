package com.fmscapstone.dto;

import java.util.List;

import com.fmscapstone.model.FeedbackType;

public class QuestionsDTO {

	private Long questionId;

	private String questionName;

	private List<AnswersDTO> answers;
	private FeedbackType feedbackType;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public List<AnswersDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswersDTO> answers) {
		this.answers = answers;
	}

	public FeedbackType getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}

	@Override
	public String toString() {
		return "QuestionsDTO [questionId=" + questionId + ", questionName=" + questionName + ", answers=" + answers
				+ ", feedbackType=" + feedbackType + "]";
	}

}
