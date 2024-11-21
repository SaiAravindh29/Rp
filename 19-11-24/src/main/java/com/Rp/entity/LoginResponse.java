package com.Rp.entity;

public class LoginResponse {
//	private String username;
//	private String password;
	private String message;
    private boolean success;
    private String redirecturl;
    private int groupid;
    private int userid;
    
    public LoginResponse() {
    	
    }
    
    public LoginResponse(String message, boolean success, String redirecturl, int groupid ,int userid ) {
        this.message = message;
        this.success = success;
        this.redirecturl = redirecturl;
        this.groupid = groupid;
        this.userid = userid;
//        this.username = username;
//        this.password = password;
    }
    

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getRedirectUrl() {
		return redirecturl;
	}

	public void setRedirectUrl(String redirecturl) {
		this.redirecturl = redirecturl;
	}

	public int getGroupId() {
		return groupid;
	}

	public void setGroupId(int groupId) {
		this.groupid = groupId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
    
    
    
}
