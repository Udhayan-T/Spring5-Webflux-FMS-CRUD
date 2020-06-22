package com.fmsevent.service;


import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Workbook;

import com.fmsevent.model.Event;
import com.fmsevent.model.EventsDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventService {
	
	public Flux<Event> getAllEventList();

	public Mono<EventsDto> findEventsById(String eventId) throws InterruptedException, ExecutionException;

	public Flux<Event> findEventsByVH(int volunteerHours);
	
	public Mono<Event>  findEventById(int id);
	
	public Workbook generateEventsExcel() throws InterruptedException, ExecutionException;
	
	public Workbook generatePocEventsExcel(String pocId) throws InterruptedException, ExecutionException;
	
	public Flux<Event> getEventsByPocId(String pocId);
}
