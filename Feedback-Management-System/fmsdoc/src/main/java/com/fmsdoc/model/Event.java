package com.fmsdoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("events")

public class Event {
	@Id
	@Column("id")
	private Long id;
	@Column("event_id")
	    private String eventId;
	@Column("month")
	private String month;
	@Column("event_name")
	    private String eventName;
	@Column("description")
	    private String description;
	@Column("startdate")
	    private String startDate;
	@Column("base_location")
	    private String baseLocation;
	@Column("beneficiary_name")
	    private String beneficiaryName;
	@Column("venue_address")
	    private String venueAddress;
	@Column("council_name")
	    private String councilName;
	@Column("project_name")
	    private String projectName;
	@Column("category")
	    private String category;
	@Column("total_volunteer")
	    private String totalVolunteer;
	@Column("total_volunteer_hour")
	    private String totalVolunteerHour;
	@Column("total_travel_hour")
	    private String totalTravelHour;
	@Column("overall_volunteer_hour")
	    private String overallVolunteerHour;
	@Column("lives_impacted")
	    private String livesImpacted;
	@Column("activity_type")
	    private String activityType;
	@Column("status")
	    private String status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getVenueAddress() {
		return venueAddress;
	}
	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}
	public String getCouncilName() {
		return councilName;
	}
	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTotalVolunteer() {
		return totalVolunteer;
	}
	public void setTotalVolunteer(String totalVolunteer) {
		this.totalVolunteer = totalVolunteer;
	}
	public String getTotalVolunteerHour() {
		return totalVolunteerHour;
	}
	public void setTotalVolunteerHour(String totalVolunteerHour) {
		this.totalVolunteerHour = totalVolunteerHour;
	}
	public String getTotalTravelHour() {
		return totalTravelHour;
	}
	public void setTotalTravelHour(String totalTravelHour) {
		this.totalTravelHour = totalTravelHour;
	}
	public String getOverallVolunteerHour() {
		return overallVolunteerHour;
	}
	public void setOverallVolunteerHour(String overallVolunteerHour) {
		this.overallVolunteerHour = overallVolunteerHour;
	}
	public String getLivesImpacted() {
		return livesImpacted;
	}
	public void setLivesImpacted(String livesImpacted) {
		this.livesImpacted = livesImpacted;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
	
}
