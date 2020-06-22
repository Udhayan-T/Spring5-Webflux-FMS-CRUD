package com.fmscapstone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FeedbackTypeDTO {
	
	private Long feedbackId;
	
	private String feedbackName;

	@Override
	public String toString() {
		return "FeedbackTypeDTO [feedbackId=" + feedbackId + ", feedbackName=" + feedbackName + "]";
	}
	
	

}
