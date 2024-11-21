package com.Rp.entity;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsBooking {
	
	@JsonProperty("UserID")
	private int UserID;
	
	@JsonProperty("TableID")
	private int TableID;
	
	@JsonProperty("BookingDate")
	private Date BookingDate;
	
	
	@JsonProperty("BookingTime")
	private Time BookingTime;
	
	@JsonProperty("message")
	private String message;
	
	public InsBooking() {
		
	}
	
	public InsBooking(int UserID, int TableID, Date BookingDate, Time BookingTime, String message) {
		
		this.UserID = UserID;
		this.TableID = TableID;
		this.BookingDate = BookingDate;
		this.BookingTime = BookingTime;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getTableID() {
		return TableID;
	}

	public void setTableID(int tableID) {
		TableID = tableID;
	}

	public Date getBookingDate() {
		return BookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		BookingDate = bookingDate;
	}

	public Time getBookingTime() {
		return BookingTime;
	}

	public void setBookingTime(Time bookingTime) {
		BookingTime = bookingTime;
	}
	
	
	
	
	

}
