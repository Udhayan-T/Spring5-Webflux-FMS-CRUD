package com.fmsevent.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fmsevent.model.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
	
	@Query("select * from events where event_id= ?")
	public Mono<Event> findByEventId(String eventId);

	@Query("select * from events where volunteer_hours= ?")
	public Flux<Event> findAllByVolunteer_hours(int vol_id);
	
	@Query("select * from events e inner join events_employees ee on e.event_id=ee.event_id where ee.employee_id= ?")
	public Flux<Event> findEventsByPocId(String pocId);

}
