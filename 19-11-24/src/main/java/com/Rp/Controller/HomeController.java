package com.Rp.Controller;

import java.util.List;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rp.entity.BookingData;
import com.Rp.entity.BookingEntry;
import com.Rp.entity.BookingTable;
import com.Rp.entity.ContactUs;
import com.Rp.entity.Dinner;
import com.Rp.entity.Lunch;
import com.Rp.entity.InsBooking;
import com.Rp.entity.LoginRequest;
import com.Rp.entity.LoginResponse;
import com.Rp.entity.SignupRequest;
import com.Rp.entity.SignupResponse;
import com.Rp.service.Userservice;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/Rp/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class HomeController {

	
	@Autowired 
	private Userservice service;

//	@PostMapping("/login")
//	public ResponseEntity<LoginResponse> log(@RequestBody LoginRequest request,HttpServletResponse response) {
//		
//		LoginResponse lr = new LoginResponse();
//		LoginRequest lgr = new LoginRequest();
//		
//		String username = request.getUsername();
//		String password = request.getPassword();
//
//		LoginRequest res = service.validateUser(username, password);
//		
//		if (res.getresultMessage() != null) {
//			
//			lr.setMessage(res.getresultMessage());
//			lr.setRedirectUrl("/home");
//			lr.setSuccess(true);
//			lr.setUserid(res.getUserid());
//			lr.setGroupId(res.getGroupid());
//			
//			// Set a cookie for the user id
//			Cookie userCookie = new Cookie("userId", String.valueOf(res.getUserid()));
//			userCookie.setSecure(false);  // Make sure Secure flag is false if you're using HTTP
//			userCookie.setPath("/"); // Make the cookie accessible to the whole domain
//			userCookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day
//			userCookie.setHttpOnly(true); // Set HttpOnly flag for security
//			// userCookie.setSecure(true); // Only if you're using HTTPS
//			//userCookie.setSameSite("None");  // Allow cookie to be sent in cross-origin requests
//		
//			response.addCookie(userCookie);
//
//			// Set a cookie for the username
//			Cookie usernameCookie = new Cookie("username", username);
//			usernameCookie.setPath("/"); // Make the cookie accessible to the whole domain
//			usernameCookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day
//			usernameCookie.setSecure(false);
//			usernameCookie.setHttpOnly(true); // Set HttpOnly flag for security
//			//usernameCookie.setSameSite("None");
//			// usernameCookie.setSecure(true); // Only if you're using HTTPS
//			response.addCookie(usernameCookie);
//            
//            System.out.println("userid *** "+userCookie.getValue());
//            System.out.println("username *** "+usernameCookie.getValue());
//			
//			if(!res.getresultMessage().equals("success") && !res.getresultMessage().equals("block") && !res.getresultMessage().equals("Admin Login success") ) {			
//				lr.setMessage(res.getresultMessage());
//				lr.setRedirectUrl(null);
//				lr.setSuccess(false);
//				lr.setUserid(0);
//				lr.setGroupId(0);	
//			}
//				
//			return ResponseEntity.ok(lr);
//
//		} else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid credentials", false, null,0,0)); // Invalid credentials
//		}
//		
//		
//	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> log(@RequestBody LoginRequest request,HttpServletResponse response) {
		
		LoginResponse lr = new LoginResponse();
		LoginRequest lgr = new LoginRequest();
		
		String username = request.getUsername();
		String password = request.getPassword();

		LoginRequest res = service.validateUser(username, password);
		
		if (res.getresultMessage() != null) {
			
			lr.setMessage(res.getresultMessage());
			lr.setRedirectUrl("/home");
			lr.setSuccess(true);
			lr.setUserid(res.getUserid());
			lr.setGroupId(res.getGroupid());
			
			// Set a cookie for the user id
			Cookie userCookie = new Cookie("userId", String.valueOf(res.getUserid()));
			userCookie.setSecure(false);  // Make sure Secure flag is false if you're using HTTP
			userCookie.setPath("/"); // Make the cookie accessible to the whole domain
			userCookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day
			userCookie.setHttpOnly(true); // Set HttpOnly flag for security
			// userCookie.setSecure(true); // Only if you're using HTTPS
			//userCookie.setSameSite("None");  // Allow cookie to be sent in cross-origin requests
		
			response.addCookie(userCookie);

			// Set a cookie for the username
			Cookie usernameCookie = new Cookie("username", username);
			usernameCookie.setPath("/"); // Make the cookie accessible to the whole domain
			usernameCookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day
			usernameCookie.setSecure(false);
			usernameCookie.setHttpOnly(true); // Set HttpOnly flag for security
			//usernameCookie.setSameSite("None");
			// usernameCookie.setSecure(true); // Only if you're using HTTPS
			response.addCookie(usernameCookie);
            
            System.out.println("userid *** "+userCookie.getValue());
            System.out.println("username *** "+usernameCookie.getValue());
			
			if(!res.getresultMessage().equals("success") && !res.getresultMessage().equals("block") && !res.getresultMessage().equals("Admin Login success") ) {			
				lr.setMessage(res.getresultMessage());
				lr.setRedirectUrl(null);
				lr.setSuccess(false);
				lr.setUserid(0);
				lr.setGroupId(0);	
			}
				
			return ResponseEntity.ok(lr);

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid credentials", false, null,0,0)); // Invalid credentials
		}
		
		
	}

	
	@GetMapping("/profile")
    public ResponseEntity<String> getUserProfile(HttpServletRequest request) {
        String userId = getCookieValue(request, "userId");
        String username = getCookieValue(request, "username");

        if (userId != null && username != null) {
            return ResponseEntity.ok("User ID: " + userId + ", Username: " + username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("No valid session found.");
        }
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
	
	

	@PostMapping("/signup") // The endpoint that accepts JSON requests
	    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) {
	        String username = request.getUsername();
	        String email = request.getEmail();
	        String password = request.getPassword();
	        String confirmPassword = request.getConfirmPassword();
	        SignupResponse sr = new SignupResponse();

	        // Call the service method to validate and create the user
	        String isSignupSuccessful = service.validateSignup(username, email, password, confirmPassword);

	        if (isSignupSuccessful!=null) {
	        	
	        	sr.setMessage(isSignupSuccessful);
	        	sr.setSuccess(true);
	        	sr.setRedirectUrl("/login"); 
	        	if("Username already exists".equals(isSignupSuccessful) || "Email already exists".equals(isSignupSuccessful)||"Password and confirm password does not match".equals(isSignupSuccessful)) {
	        		sr.setRedirectUrl(null);
	        		sr.setSuccess(false);
	        	}
	            return ResponseEntity.ok(sr);
	        } else {
	        	
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new SignupResponse("Error: Passwords do not match or other validation failed", false, null));
	        }
	    }


	@PostMapping("/booking")
	public ResponseEntity<LoginResponse> bookingEntry(@RequestBody BookingEntry book) {

		String res = service.insertBooking(book);
		
		if (res != null) {
			LoginResponse lr = new LoginResponse();
			lr.setMessage(res);
			lr.setRedirectUrl("/home");
			lr.setSuccess(true);
			return ResponseEntity.ok(lr);

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new LoginResponse("Invalid credentials",false,null,0,0)); // Invalid credentials
		}

	}
    
    
    @GetMapping("/showDinnerSlots")
  	public ResponseEntity<Dinner> showTimeSlots() {

  		Dinner res = service.getDinnerTimings();
  		
  		if (res != null) {
  			return ResponseEntity.ok(res);

  		} else {
  			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
  					.body(null); // Invalid credentials
  		}

  	}

	@GetMapping("/showLunchSlots")
  	public ResponseEntity<Lunch> showLunchTimeSlots() {

  		Lunch res = service.getLunchTimings();
  		
  		if (res != null) {
  			return ResponseEntity.ok(res);

  		} else {
  			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
  					.body(null); // Invalid credentials
  		}

  	}

	@GetMapping("/showBookingTable")
  	public ResponseEntity<List<BookingEntry>> showBooking() {
    	
    	List<BookingEntry> res = service.getBookingDetails();
  		
  		if (res != null) {
  			return ResponseEntity.ok(res);

  		} else {
  			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
  					.body(null); // Invalid credentials
  		}

  	}
	 
	   @PostMapping("/UpdateBookingtable") //ragul codes
		public ResponseEntity<Void> updateBookingtable(@RequestParam int groupid, @RequestBody BookingEntry Dinetable ) {

			boolean updated = service.updateBookingtable(Dinetable, groupid);

			return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
		}
	   
	   @PostMapping("/DeleteBookingtable") 
		public ResponseEntity<String> deleteBookingtable(@RequestParam int bookingId ) {

			String updated = service.deleteBookingtable(bookingId);

			if (updated != null) {
	  			return ResponseEntity.ok(updated);

	  		} else {
	  			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	  					.body(null); // Invalid credentials
	  		}
		}

    

    @GetMapping("/userTable") //ragul codes
	public ResponseEntity<List<Map<String, Object>>> postMethodName(@RequestParam int groupid) {

		List<Map<String, Object>> table = service.fetchuser(groupid);
		if (table.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(table);
	}
    
    @PostMapping("/block")
	public ResponseEntity<String> Blockuser(@RequestParam String username,@RequestParam int groupid){
		boolean updated = service.Blockuser(username,groupid);
		
		return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	@PostMapping("/contact")
	public ResponseEntity<String> Contact(@RequestBody ContactUs contact){
		boolean updated = service.Contact(contact);
		
		return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/showContact")
	public ResponseEntity<List<ContactUs>> getContact(){
		
		List<ContactUs> res = service.showAllContact();
		if (res.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(res);
	
	}



	


}
