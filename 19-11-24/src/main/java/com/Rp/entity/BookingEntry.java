package com.Rp.entity;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingEntry {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("phone_number")
	private String phone_number;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("bookingDate")
	private Date bookingDate;
	
	@JsonProperty("bookingTime")
	private String bookingTime;
	
	@JsonProperty("cuisine_type")
	private String cuisine_type;
	
	@JsonProperty("NumberofGuest")
	private int NumberofGuest;
	
	@JsonProperty("foodTiming")
	private String foodTiming;
	
	@JsonProperty("tableType")
	private String tableType;
	
	@JsonProperty("indoor")
	private String indoor;
	
	private String outdoor;
	
	@JsonProperty("is_private")
	private String is_private;
	
	@JsonProperty("event")
	private String event;
	
	@JsonProperty("resultMessage")
	private String resultMessage; 
	
	@JsonProperty("bookingId")
	private int bookingId; 
	
	
	public BookingEntry() {
		
	}


	public BookingEntry(String name, String phone_number, String email, Date bookingDate, String bookingTime,
			String cuisine_type, int NumberofGuest, String foodTiming, String tableType, String indoor, String outdoor,
			String is_private, String event, String resultMessage, int bookingId) {
		
		this.name = name;
		this.phone_number = phone_number;
		this.email = email;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.cuisine_type = cuisine_type;
		this.NumberofGuest = NumberofGuest;
		this.foodTiming = foodTiming;
		this.tableType = tableType;
		this.indoor = indoor;
		this.outdoor = outdoor;
		this.is_private = is_private;
		this.event = event;
		this.resultMessage = resultMessage;
		this.bookingId = bookingId;
	}
	
	// getters and setters

	
	
	
	public String getName() {
		return name;
	}


	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}


	public String getBookingTime() {
		return bookingTime;
	}


	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}


	public String getCuisine_type() {
		return cuisine_type;
	}


	public void setCuisine_type(String cuisine_type) {
		this.cuisine_type = cuisine_type;
	}


	public int getNumberofGuest() {
		return NumberofGuest;
	}


	public void setNumberofGuest(int NumberofGuest) {
		this.NumberofGuest = NumberofGuest;
	}


	public String getFoodTiming() {
		return foodTiming;
	}


	public void setFoodTiming(String foodTiming) {
		this.foodTiming = foodTiming;
	}


	public String getTableType() {
		return tableType;
	}


	public void setTableType(String tableType) {
		this.tableType = tableType;
	}


	public String getIndoor() {
		return indoor;
	}


	public void setIndoor(String indoor) {
		this.indoor = indoor;
	}


	public String getOutdoor() {
		return outdoor;
	}


	public void setOutdoor(String outdoor) {
		this.outdoor = outdoor;
	}


	public String getIs_private() {
		return is_private;
	}


	public void setIs_private(String is_private) {
		this.is_private = is_private;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getResultMessage() {
		return resultMessage;
	}


	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	
	
	
}
