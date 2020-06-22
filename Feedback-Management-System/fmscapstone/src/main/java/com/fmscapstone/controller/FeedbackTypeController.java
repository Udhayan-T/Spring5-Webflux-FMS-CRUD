package com.fmscapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmscapstone.model.FeedbackType;
import com.fmscapstone.service.FeedbackServiceImpl;

import reactor.core.publisher.Flux;



@RestController
@CrossOrigin("*")
@RequestMapping("/feedbackType")
public class FeedbackTypeController {
	@Autowired
	FeedbackServiceImpl feedbackServiceImpl;
	
	@GetMapping()
	public Flux<FeedbackType> getFeedbackType(){
		
		return feedbackServiceImpl.getAllFeedbackTypes();
	}
}
