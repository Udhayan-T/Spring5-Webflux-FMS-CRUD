package com.fmscapstone.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmscapstone.model.FeedbackResponse;

import reactor.core.publisher.Flux;

public interface FeedbackResponseRepository extends ReactiveCrudRepository<FeedbackResponse, Long> {
@Query("select * from feedback_response where event_id=? and employee_id=?")
Flux<FeedbackResponse> findResponseByEventIdAndEmployeeId(String eventId,String employeeId);
}
