package com.fmsevent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("events_employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventEmployee{
	
	@Id
	private Long id;
	@Column("employee_id")
	private String employeeId;
	@Column("employee_name")
	private String employeeName;
	@Column("contact_number")
	private String contactNumber;
	@Column("employee_type")
	private String employee_type;
	@Column("event_id")
	private String event_id;
}
