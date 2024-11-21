package com.Rp.entity;

public class LoginRequest {
	
	private String username;
	private String password;
	private int userid;
	private int groupid;
	private String resultMessage;
	
	public LoginRequest() {
		
	}

	public LoginRequest(String username,String password,int userid,int groupid,String resultMessage) {
		this.username=username;
		this.password=password;
		this.userid=userid;
		this.groupid=groupid;
		this.resultMessage = resultMessage;
	}

	public String getUsername() {
		return username;
	}
	public String getresultMessage() {
		return resultMessage;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setresultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	
	
}
