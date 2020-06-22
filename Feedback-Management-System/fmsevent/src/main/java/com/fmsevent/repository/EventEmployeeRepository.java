package com.fmsevent.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmsevent.model.EventEmployee;

import reactor.core.publisher.Flux;

public interface EventEmployeeRepository extends ReactiveCrudRepository<EventEmployee, Long>{
	@Query("select * from events_employees where event_id= ?")
	public Flux<EventEmployee> findByEventId(String eventId);
}
