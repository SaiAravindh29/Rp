package com.Rp.entity;

import java.util.List;

public class Lunch {
	
	 private List<String> lunchIndoor;
	 private List<String> lunchOutdoor;
	 private List<String> lunchPrivate;
	 
	 
	 public Lunch() {
		 
	 }


	public Lunch(List<String> lunchIndoor, List<String> lunchOutdoor, List<String> lunchPrivate) {
	
		this.lunchIndoor = lunchIndoor;
		this.lunchOutdoor = lunchOutdoor;
		this.lunchPrivate = lunchPrivate;
	}


	public List<String> getLunchIndoor() {
		return lunchIndoor;
	}


	public void setLunchIndoor(List<String> lunchIndoor) {
		this.lunchIndoor = lunchIndoor;
	}


	public List<String> getLunchOutdoor() {
		return lunchOutdoor;
	}


	public void setLunchOutdoor(List<String> lunchOutdoor) {
		this.lunchOutdoor = lunchOutdoor;
	}


	public List<String> getLunchPrivate() {
		return lunchPrivate;
	}


	public void setLunchPrivate(List<String> lunchPrivate) {
		this.lunchPrivate = lunchPrivate;
	}
	
	
	 

}
