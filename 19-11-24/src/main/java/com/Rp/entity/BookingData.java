package com.Rp.entity;

public class BookingData {
	
	private int BookingId;
	private String resultMessage;
	
	public BookingData() {
		
	}
	
	public BookingData(int BookingId, String resultMessage) {
	
		this.BookingId = BookingId;
		this.resultMessage = resultMessage;
	}

	public int getBookingId() {
		return BookingId;
	}

	public void setBookingId(int BookingId) {
		this.BookingId = BookingId;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	
	
	

}
