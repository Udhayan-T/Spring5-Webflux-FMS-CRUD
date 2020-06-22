package com.fmsevent.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmsevent.model.FeedbackResponse;

import reactor.core.publisher.Flux;

public interface FeedbackResponseRepository extends ReactiveCrudRepository<FeedbackResponse, Long> {
	@Query("select * from feedback_response where event_id=?")
	Flux<FeedbackResponse> getFeedbackResponses(String eventId);

}
