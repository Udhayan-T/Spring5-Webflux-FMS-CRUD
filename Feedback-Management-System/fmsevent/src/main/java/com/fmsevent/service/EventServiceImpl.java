package com.fmsevent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmsevent.model.Event;
import com.fmsevent.model.EventEmployee;
import com.fmsevent.model.EventsDto;
import com.fmsevent.model.FeedbackResponse;
import com.fmsevent.repository.EventEmployeeRepository;
import com.fmsevent.repository.EventRepository;
import com.fmsevent.repository.FeedbackResponseRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventServiceImpl implements EventService {

	private static String[] columns = { "Event ID", "Month", "Base Location","Beneficiary Name","Venue Address", "Council Name","Project",
			"Category","Event Name","Event Description", "Event Date (DD-MM-YY)","Total no. of volunteers","Total Volunteer Hours", "Total Travel Hours",
			"Overall Volunteer Hours","Lives Impacted", "Activity Type", "Status"};
	

	List<Event> output = new ArrayList<Event>();

	@Autowired
	public EventRepository eventRepo;
	
	@Autowired
	public EventEmployeeRepository eventEmployeeRepository;
	
	@Autowired
	public FeedbackResponseRepository feedbackResponseRepository;

	@Override
	public Flux<Event> getAllEventList() {
		Flux<Event> events = eventRepo.findAll();
		return events;
	}

	@Override
	public Mono<EventsDto> findEventsById(String eventId) throws InterruptedException, ExecutionException {
		Event event=eventRepo.findByEventId(eventId).toFuture().get();
		List<EventEmployee> eventEmployeeList=eventEmployeeRepository.findByEventId(eventId).collectList().toFuture().get();
		List<FeedbackResponse> feedbackResponses=feedbackResponseRepository.getFeedbackResponses(eventId).collectList().toFuture().get();
		System.out.println(feedbackResponses);
			EventsDto eventDto = new EventsDto();
			eventDto.setEvent(event);
			eventDto.setFeedbackResponses(feedbackResponses);
			eventDto.setEventEmployeeList(eventEmployeeList);
		return Mono.just(eventDto);
	}

	@Override
	public Flux<Event> findEventsByVH(int volunteerHours) {
		return eventRepo.findAllByVolunteer_hours(volunteerHours);
	}
	
	@Override
	public Mono<Event> findEventById(int id) {
		return eventRepo.findById((long) id);
	}

	
	@Override
	public Workbook generateEventsExcel() throws InterruptedException, ExecutionException {
		List<Event> values = eventRepo.findAll().collectList().toFuture().get();
		
		System.out.println(output);
		return copyToExcel(values);
	}
	
	@Override
	public Workbook generatePocEventsExcel(String pocId) throws InterruptedException, ExecutionException {
		List<Event> values = eventRepo.findEventsByPocId(pocId).collectList().toFuture().get();
		
		System.out.println(output);
		return copyToExcel(values);
	}

	private Workbook copyToExcel(List<Event> output) {

		Workbook workbook = new XSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Events Report");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		int rowNum = 1;
		for (Event event : output) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(event.getEventId());


			row.createCell(1).setCellValue(event.getMonth());
			
			row.createCell(2).setCellValue(event.getBaseLocation());

			row.createCell(3).setCellValue(event.getBeneficiaryName());
			
			row.createCell(4).setCellValue(event.getVenueAddress());

			row.createCell(5).setCellValue(event.getCouncilName());

			row.createCell(6).setCellValue(event.getProjectName());
			
			row.createCell(7).setCellValue(event.getCategory());
			
			row.createCell(8).setCellValue(event.getEventName());

			row.createCell(9).setCellValue(event.getDescription());

			Cell dateOfBirthCell = row.createCell(10);
			dateOfBirthCell.setCellValue(event.getStartDate());
			dateOfBirthCell.setCellStyle(dateCellStyle);


			row.createCell(11).setCellValue(event.getTotalVolunteer());

			row.createCell(12).setCellValue(event.getTotalVolunteerHour());

			row.createCell(13).setCellValue(event.getTotalTravelHour());

			row.createCell(14).setCellValue(event.getOverallVolunteerHour());
			
			row.createCell(15).setCellValue(event.getLivesImpacted());
			
			row.createCell(16).setCellValue(event.getActivityType());
			
			row.createCell(17).setCellValue(event.getStatus());
		}

		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		return workbook;
	}

	@Override
	public Flux<Event> getEventsByPocId(String pocId) {

		return eventRepo.findEventsByPocId(pocId);
	}


}
