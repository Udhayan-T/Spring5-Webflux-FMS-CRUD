package com.fmscapstone.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmscapstone.model.FeedbackType;

public interface FeedbackRepository extends ReactiveCrudRepository<FeedbackType, Long>{

}
