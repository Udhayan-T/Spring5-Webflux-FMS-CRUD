package com.fmscapstone.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmscapstone.model.Answers;

import reactor.core.publisher.Flux;

public interface AnswersRepository extends ReactiveCrudRepository<Answers, Long> {
	@Query("Select * from answers as a Inner join questions as q on a.q_id= q.q_id where a.q_id=?")
	Flux<Answers> findAnswersByQuestionId(Long id);

}
