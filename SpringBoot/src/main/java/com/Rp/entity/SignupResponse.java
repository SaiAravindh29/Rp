package com.Rp.entity;

public class SignupResponse {
    private String message;
    private boolean success;
    private String redirectUrl;
     
    public SignupResponse() {
    	
    }
    // Constructor
    public SignupResponse(String message, boolean success, String redirectUrl) {
        this.message = message;
        this.success = success;
        this.redirectUrl = redirectUrl;
    }

    // Getters and Setters
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
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
