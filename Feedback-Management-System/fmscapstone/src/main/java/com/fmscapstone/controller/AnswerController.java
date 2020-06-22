package com.fmscapstone.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmscapstone.dto.AnswerRequestDTO;
import com.fmscapstone.model.Answers;
import com.fmscapstone.service.FeedbackServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
@RequestMapping("/answer")
public class AnswerController {
	
	
	
	@Autowired
	FeedbackServiceImpl feedbackServiceImpl;
	
	
	@PostMapping()
	public Mono<Answers> saveAnswers(@RequestBody Answers answer){
		return feedbackServiceImpl.saveAnswer(answer);
	}
	
	@PatchMapping("/saveall/{questionId}")
	public Flux<Answers> bulkSaveAnswers(@PathVariable Long questionId, @RequestBody AnswerRequestDTO answers) throws InterruptedException, ExecutionException{
		return feedbackServiceImpl.saveAnswers(questionId, answers);
	}
	
	@DeleteMapping("/{answerId}")
	public Mono<Void> deleteAnswer(@PathVariable Long answerId){
		return feedbackServiceImpl.deleteAnswerById(answerId);
	}
	
	@GetMapping("/{questionId}")
	public Flux<Answers> getAllAnswersById(@PathVariable Long questionId)
	{
		return feedbackServiceImpl.getAnswersByQuestionId(questionId);
	}
	
}
