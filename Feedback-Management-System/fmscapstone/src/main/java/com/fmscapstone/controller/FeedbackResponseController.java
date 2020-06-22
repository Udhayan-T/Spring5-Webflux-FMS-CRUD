package com.fmscapstone.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmscapstone.model.FeedbackResponse;
import com.fmscapstone.service.FeedbackServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/feedbackresponse")
public class FeedbackResponseController {
	
	@Autowired
	FeedbackServiceImpl feedbackServiceImpl;
	
	
	@PostMapping("")
	public ResponseEntity<?> saveFeedbackResponse(@RequestBody List<FeedbackResponse> feedbackResponses) throws InterruptedException, ExecutionException{
		String eventId=feedbackResponses.stream().map(FeedbackResponse::getEventId).findFirst().get();
		String employeeId=feedbackResponses.stream().map(FeedbackResponse::getEmployeeId).findFirst().get();
		if(feedbackServiceImpl.isEmployeeExist(eventId, employeeId).collectList().toFuture().get().isEmpty()) {	
			return new ResponseEntity<>(feedbackServiceImpl.saveFeedbackResponse(feedbackResponses), HttpStatus.OK);	
		}
		
		return new ResponseEntity<>( HttpStatus.CONFLICT);
		}
	

}
