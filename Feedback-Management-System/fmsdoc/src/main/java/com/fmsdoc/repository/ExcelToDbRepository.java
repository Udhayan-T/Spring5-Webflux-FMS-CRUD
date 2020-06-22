package com.fmsdoc.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmsdoc.model.Event;

import reactor.core.publisher.Mono;

public interface ExcelToDbRepository extends ReactiveCrudRepository<Event, Long>{

	@Query("select * from events where event_id=?")
	Mono<Event> findByEventId(String eventId);

}
