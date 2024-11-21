package com.Rp.entity;

import java.util.List;

public class Dinner{
	
	 private List<String> dinnerIndoor;
	 private List<String> dinnerOutdoor;
	 private List<String> dinnerPrivate;
	 
	 
	 public Dinner() {
		 
	 }


	public Dinner(List<String> dinnerIndoor, List<String> dinnerOutdoor, List<String> dinnerPrivate) {
	
		this.dinnerIndoor = dinnerIndoor;
		this.dinnerOutdoor = dinnerOutdoor;
		this.dinnerPrivate = dinnerPrivate;
	}


	public List<String> getDinnerIndoor() {
		return dinnerIndoor;
	}


	public void setDinnerIndoor(List<String> dinnerIndoor) {
		this.dinnerIndoor = dinnerIndoor;
	}


	public List<String> getDinnerOutdoor() {
		return dinnerOutdoor;
	}


	public void setDinnerOutdoor(List<String> dinnerOutdoor) {
		this.dinnerOutdoor = dinnerOutdoor;
	}


	public List<String> getDinnerPrivate() {
		return dinnerPrivate;
	}


	public void setDinnerPrivate(List<String> dinnerPrivate) {
		this.dinnerPrivate = dinnerPrivate;
	}
	
	
	 
	 
	 
	
}