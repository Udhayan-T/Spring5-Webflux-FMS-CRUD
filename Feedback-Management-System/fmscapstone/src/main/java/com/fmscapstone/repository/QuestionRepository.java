package com.fmscapstone.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmscapstone.model.Questions;

import reactor.core.publisher.Flux;

public interface QuestionRepository extends ReactiveCrudRepository<Questions, Long>{

	@Query("Select * from questions as q Inner join feedback as f on q.f_key= f.f_key where f.f_type=?")
	Flux<Questions> findQuestionsByFeedbackType(String feedbackType);

}
