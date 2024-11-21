package com.Rp.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingTable {
	
	private String name;
	private String phone_number;
	private String email;
	private String cuisine_type;
	private String tableType;
	private String indoor;
	private String outdoor;
	
	@JsonProperty("is_private")
	private String is_private;
	
	
	private Date bookingDate;
	private Time bookingTime;
	
    @JsonProperty("NumberofGuest")
	private String NumberofGuest;
   
    @JsonProperty("bookingId")
    private int bookingId;
	
	public BookingTable() {
		
	}

	public BookingTable(String name, String phone_number, String email, String cuisine_type, String tableType,String indoor, String outdoor, String is_private, Date bookingDate, Time bookingTime,String NumberofGuest, int bookingId) {
		this.name = name;
		this.phone_number = phone_number;
		this.email = email;
		this.cuisine_type = cuisine_type;
		this.tableType = tableType;
		this.indoor = indoor;
		this.outdoor = outdoor;
		this.is_private = is_private;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.NumberofGuest = NumberofGuest;
		this.bookingId = bookingId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getName() {
		return name;
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

	public String getCuisine_type() {
		return cuisine_type;
	}

	public void setCuisine_type(String cuisine_type) {
		this.cuisine_type = cuisine_type;
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

//	public String getPrivate() {
//		return is_private;
//	}
//
//	public void setPrivate(String is_private) {
//		this.is_private = is_private;
//	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Time getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}

//	public String getNumberofGuest() {
//		return NumberofGuest;
//	}
//
//	public void setNumberofGuest(String NumberofGuest) {
//		this.NumberofGuest = NumberofGuest;
//	}
//	

	
	   public String getPrivate() {
	        return is_private;
	    }

	    public void setPrivate(String is_private) {
	        this.is_private = is_private;
	    }

	    // Getter and Setter for NumberofGuest
	    public String getNumberofGuest() {
	        return NumberofGuest;
	    }

	    public void setNumberofGuest(String numberOfGuest) {
	        this.NumberofGuest = numberOfGuest;
	    }
	
	
}
