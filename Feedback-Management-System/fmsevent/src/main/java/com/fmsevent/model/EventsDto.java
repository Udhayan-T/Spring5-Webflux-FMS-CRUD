package com.fmsevent.model;


import java.util.List;

import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class EventsDto {
	
	private Event event;
	private List<FeedbackResponse> feedbackResponses;
	private List<EventEmployee> eventEmployeeList;
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public List<FeedbackResponse> getFeedbackResponses() {
		return feedbackResponses;
	}
	public void setFeedbackResponses(List<FeedbackResponse> feedbackResponses) {
		this.feedbackResponses = feedbackResponses;
	}
	public List<EventEmployee> getEventEmployeeList() {
		return eventEmployeeList;
	}
	public void setEventEmployeeList(List<EventEmployee> eventEmployeeList) {
		this.eventEmployeeList = eventEmployeeList;
	}
	
	

}
