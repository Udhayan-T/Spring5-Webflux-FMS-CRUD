package com.fmsdoc.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fmsdoc.model.Event;
import com.fmsdoc.model.EventEmployee;
import com.fmsdoc.model.User;
import com.fmsdoc.repository.EventEmployeeRepository;
import com.fmsdoc.repository.ExcelToDbRepository;
import com.fmsdoc.repository.UserRepository;

public class FmsDocumentController {

	@Autowired
	ExcelToDbRepository excelToDbRepository;
	
	@Autowired
	EventEmployeeRepository eventEmployeeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("")
	public String importEvents() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, ExecutionException{
		
		Workbook workbook=WorkbookFactory.create(new File("C:/Users/ADHAVAN/Desktop/CTS_React/outreachsummary.xlsx"));
		Sheet sheet=workbook.getSheetAt(0);
		int index=0;
		DataFormatter dataFormatter=new DataFormatter();
		
		for(Row row:sheet){
			
			if(index!=0)
			{
			String eventId=dataFormatter.formatCellValue(row.getCell(0));
			System.out.println(eventId);
			String month=dataFormatter.formatCellValue(row.getCell(1));
			String baseLocation=dataFormatter.formatCellValue(row.getCell(2));
			String beneficiaryName=dataFormatter.formatCellValue(row.getCell(3));
			String venueAddress=dataFormatter.formatCellValue(row.getCell(4));
			String councilName=dataFormatter.formatCellValue(row.getCell(5));
			String projectName=dataFormatter.formatCellValue(row.getCell(6));
			String category=dataFormatter.formatCellValue(row.getCell(7));
			String eventName=dataFormatter.formatCellValue(row.getCell(8));
			System.out.println(eventName);
			String description=dataFormatter.formatCellValue(row.getCell(9));
			String startDate=dataFormatter.formatCellValue(row.getCell(10));
			String totalVolunteer=dataFormatter.formatCellValue(row.getCell(11));
			String totalVolunteerHour=dataFormatter.formatCellValue(row.getCell(12));
			String totalTravelHour=dataFormatter.formatCellValue(row.getCell(13));
			String overallVolunteerHour=dataFormatter.formatCellValue(row.getCell(14));
			String livesImpacted=dataFormatter.formatCellValue(row.getCell(15));
			String activityType=dataFormatter.formatCellValue(row.getCell(16));
			String status=dataFormatter.formatCellValue(row.getCell(17));
			String employeeId=dataFormatter.formatCellValue(row.getCell(18));
			String employeeName=dataFormatter.formatCellValue(row.getCell(19));
			String contactNumber=dataFormatter.formatCellValue(row.getCell(20));
			
		Event event=excelToDbRepository.findByEventId(eventId).toFuture().get();
			if(event==null){
				 event=new Event();
			event.setEventId(eventId);
			event.setMonth(month);
			event.setEventName(eventName);
			event.setActivityType(activityType);
			event.setBaseLocation(baseLocation);
			event.setBeneficiaryName(beneficiaryName);
			event.setCategory(category);
			event.setCouncilName(councilName);
			event.setDescription(description);
			event.setLivesImpacted(livesImpacted);
			event.setOverallVolunteerHour(overallVolunteerHour);
			event.setProjectName(projectName);
			event.setStartDate(startDate);
			event.setStatus(status);
			event.setTotalTravelHour(totalTravelHour);
			event.setTotalVolunteer(totalVolunteer);
			event.setTotalVolunteerHour(totalVolunteerHour);
			event.setVenueAddress(venueAddress);
			excelToDbRepository.save(event).toFuture();
						 
			EventEmployee eventEmployee=new EventEmployee();
			eventEmployee.setEmployeeId(employeeId);
			eventEmployee.setEmployeeName(employeeName);
			eventEmployee.setContactNumber(contactNumber);
			eventEmployee.setEmployee_type("POC");
			eventEmployee.setEvent_id(event.getEventId());
			eventEmployeeRepository.save(eventEmployee).toFuture();
			
			User user=new User();
			user.setUsername(employeeId);
			user.setFirstname(employeeName);
			user.setPassword(employeeId);
			user.setRole("POC");
			userRepository.save(user).toFuture();
           
			}
			}
			index++;
			
		}
		workbook.close();
		
		
		return "imported";
	}

}
