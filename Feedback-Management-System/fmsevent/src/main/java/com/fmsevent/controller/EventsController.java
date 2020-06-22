package com.fmsevent.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fmsevent.model.Event;
import com.fmsevent.model.EventsDto;
import com.fmsevent.service.EventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
public class EventsController {

	@Autowired
	private EventService eventService;

	@GetMapping("/getEvents")
	public Flux<Event> getEvents() {
		return eventService.getAllEventList();
	}

	@GetMapping("/getEvents/{eventId}")
	public Mono<EventsDto> getEventsById(@PathVariable String eventId) throws InterruptedException, ExecutionException {
		return eventService.findEventsById(eventId);
	}
	
	@GetMapping("/getEventsById/{id}")
	public Mono<Event> getEventsById(@PathVariable int id) {
		return eventService.findEventById(id);
	}

	@GetMapping("/getEventsByVh/{vh}")
	public Flux<Event> getEventsByVolunteerHours(@PathVariable int vh) {
		return eventService.findEventsByVH(vh);
	}

	
	
	@GetMapping("/downloadExcel")
	public  ResponseEntity<byte[]> downloadExcel() throws IOException, InterruptedException, ExecutionException {
		
		Workbook workbook = eventService.generateEventsExcel();
		OutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] media = ((ByteArrayOutputStream) outputStream).toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.valueOf("text/html"));
        headers.set("Content-disposition", "attachment; filename=Events.xlsx");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;

	}
	
	
	@GetMapping("/downloadPocExcel/{pocId}")
	public ResponseEntity<byte[]> downloadPocExcel(@PathVariable String pocId, ServerHttpResponse response) throws IOException, InterruptedException, ExecutionException {
		Workbook workbook = eventService.generatePocEventsExcel(pocId);
		OutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] media = ((ByteArrayOutputStream) outputStream).toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.valueOf("text/html"));
        headers.set("Content-disposition", "attachment; filename=POCEvents.xlsx");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
	}
	
	@GetMapping("/getPocEvents/{pocId}")
	public Flux<Event> getEventsByPocId(@PathVariable String pocId)
	{
		return eventService.getEventsByPocId(pocId);
		
	}
	
}
