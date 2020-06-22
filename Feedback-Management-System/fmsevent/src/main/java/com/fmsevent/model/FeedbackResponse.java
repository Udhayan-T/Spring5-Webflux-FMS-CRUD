package com.fmsevent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("feedback_response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {

	@Id
	private Long id;

	@Column("question_id")
	private Long questionId;

	@Column("answer")
	private String answer;

	@Column("feedback_type")
	private String feedbackType;

	@Column("event_id")
	private String eventId;

	@Column("employee_id")
	private String employeeId;
}
