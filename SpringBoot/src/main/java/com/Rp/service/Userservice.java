package com.Rp.service;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;	
import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.Rp.entity.BookingData;
import com.Rp.entity.BookingEntry;
import com.Rp.entity.BookingTable;
import com.Rp.entity.ContactUs;
import com.Rp.entity.Dinner;
import com.Rp.entity.Lunch;
import com.Rp.entity.LoginRequest;
import com.Rp.entity.InsBooking;
import com.Rp.entity.BookingTable;

@Service
public class Userservice {
		
	private static final String URL = "jdbc:sqlserver://10.50.1.136\\\\sqlexpress:1433;databaseName=Fresher;encrypt=true;trustServerCertificate=true";
	private static final String USER = "Fresher";
	private static final String PASSWORD = "Fresh@123";
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	Statement stmt = null;
	CallableStatement cs = null;
	
//	public LoginRequest validateUser(String username, String password) {
//		
//		LoginRequest lr = new LoginRequest();
//		
//		String resultMessage = null;
//		Integer userid = null;
//		Integer groupid = null;
//		
//		String sql = "{Call Rp_login_proc1234(?,?,?,?,?)}";
//		
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			cs = conn.prepareCall(sql);
//			cs.setString(1, username);  
//			cs.setString(2, password);  
//			
//			 // Register the OUTPUT parameter for the result message
//			 cs.registerOutParameter(3, Types.INTEGER);
//			 cs.registerOutParameter(4, Types.INTEGER);
//	         cs.registerOutParameter(5, Types.VARCHAR); // @resultMessage (OUTPUT)
//
//	        // Execute the stored procedure
//	        cs.execute();
//
//	        // Retrieve the value of the output parameter
//	        userid = cs.getInt(3);
//	        groupid = cs.getInt(4);
//	        resultMessage = cs.getString(5); // Retrieve the OUTPUT parameter value
//	        
//	        // Check the result and return accordingly
//	        if ("success".equals(resultMessage)) {
//	        	
//	        	lr.setresultMessage(resultMessage);
//	        	lr.setUserid(userid);
//	        	lr.setGroupid(groupid);
//	            return lr;  // Successfully registered
//	        } else {
//	        	lr.setresultMessage(resultMessage);
//	        	lr.setUserid(userid);
//	        	lr.setGroupid(groupid);
//	           return lr; // Log error message from stored procedure
//	        }
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();  // Log the exception
//	    } finally {
//	        // Close database resources to avoid memory leaks
//	        try {
//	            if (cs != null) cs.close();
//	            if (conn != null) conn.close();
//	        } catch (SQLException ex) {
//	            ex.printStackTrace();
//	        }
//	    }
//		return null;
//	}
	
	
	public LoginRequest validateUser(String username, String password) {
	
	LoginRequest lr = new LoginRequest();
	
	String storedHashedPassword = null;
	String resultMessage = null;
	Integer userid = null;
	Integer groupid = null;
	String sql = "{Call rp_loginpass(?,?,?,?)}";
	
	try {
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		cs = conn.prepareCall(sql);
		cs.setString(1, username);  
		
		
		 // Register the OUTPUT parameter for the result message
		 cs.registerOutParameter(2, Types.VARCHAR);
		 cs.registerOutParameter(3, Types.INTEGER);
         cs.registerOutParameter(4, Types.INTEGER); // @resultMessage (OUTPUT)

        // Execute the stored procedure
        cs.execute();

        // Retrieve the value of the output parameter
        storedHashedPassword = cs.getString(2);
        userid = cs.getInt(3);
        groupid = cs.getInt(4);
         // Retrieve the OUTPUT parameter value
      
        // Check the result and return accordingly
        if (storedHashedPassword == null) {
        	
        	lr.setresultMessage("Invalid username or email");
        	lr.setUserid(0);        
        	lr.setGroupid(0);
        	
           return lr;  
           
        } else if (storedHashedPassword != null && PasswordUtil.checkPassword(password, storedHashedPassword)) {
            
        	
         	lr.setresultMessage("success");
        	lr.setUserid(userid);
        	lr.setGroupid(groupid);
         
        	return lr; 
        	
        }else {
        	
        	lr.setresultMessage("Invalid password");
        	lr.setUserid(0);        
        	lr.setGroupid(0);
        	
           return lr;  
        }

    } catch (SQLException e) {
        e.printStackTrace();  // Log the exception
    } finally {
        // Close database resources to avoid memory leaks
        try {
            if (cs != null) cs.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	return null;
}
	
	
	
	public String validateSignup(String username, String email, String password, String confirmPassword) {
	    String sql = "{Call check_signup(?, ?, ?, ?, ?)}"; 
	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        cs = conn.prepareCall(sql);

	        // Set input parameters for stored procedure
	        cs.setString(1, username);      
	        cs.setString(2, email);        
	        cs.setString(3, password);      
	        cs.setString(4, confirmPassword); 
	               
	        cs.registerOutParameter(5, Types.VARCHAR); // @resultMessage (OUTPUT)

	        // Execute the stored procedure
	        cs.execute();
	        
	        String resultMessage = cs.getString(5); // Retrieve the OUTPUT parameter value
	               
	        if ("User registered successfully".equals(resultMessage)) {
	            return resultMessage; 
	        } else {
	            return resultMessage;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {	        
	        try {
	            if (cs != null) cs.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return null; 
	}


	
//	public String insertBooking(BookingTable book) {
//		
//	    String sql = "{Call  Rp_bookingTable_proc(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // Stored procedure with OUTPUT parameter
//	    
//	    CallableStatement cs = null; // Declare as CallableStatement
//	    ResultSet rs = null; // ResultSet (if needed)
//	    Connection conn = null;
//	    
//	    try {
//	        
//	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//	        cs = conn.prepareCall(sql);
//	        
//	        cs.setString(1, book.getName());      
//	        cs.setString(2, book.getPhone_number());         
//	        cs.setString(3, book.getEmail());    
//	        cs.setDate(4, book.getBookingDate());
//	        cs.setTime(5, book.getBookingTime());
//	        cs.setString(6, book.getNumberofGuest());
//	        cs.setString(7, book.getCuisine_type());
//	        cs.setString(8, book.getTableType());
//	        cs.setString(9, book.getIndoor());
//	        cs.setString(10, book.getOutdoor());
//	        cs.setString(11, book.getPrivate());
//	        cs.setInt(12, book.getBookingId());
//	        
//	        cs.registerOutParameter(13, Types.VARCHAR);
//	        cs.execute();
//	        
//	        String resultMessage = cs.getString(13);
//	        
//	        if ("Booking Successful".equals(resultMessage)) {
//	            return resultMessage; 
//	        } else {
//	            System.out.println(resultMessage);
//	            return resultMessage;
//	        }
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();  
//	    } finally {
//	        
//	        try {
//	            if (cs != null) cs.close();
//	            if (conn != null) conn.close();
//	        } catch (SQLException ex) {
//	            ex.printStackTrace();
//	        }
//	    }
//
//	    return null; 
//	}
	
	
//	public BookingData insertBooking2(InsBooking book) {
//		
//		String sql = "{ Call Rp_InsertBooking_proc(?, ?, ?, ?, ?, ?) }";
//		BookingData bd = new BookingData();
//		
//		    CallableStatement cs = null; // Declare as CallableStatement
//		    ResultSet rs = null; // ResultSet (if needed)
//		    Connection conn = null;
//		    
//		    System.out.println("201");
//		    
//		    System.out.println("user id : "+book.getUserID());
//		    System.out.println("table id : "+book.getTableID());
//		    System.out.println("booking date : "+book.getBookingDate());
//		    System.out.println("booking time : "+book.getBookingTime());
//		    
//		    try {
//		        
//		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
//		     
//		        cs = conn.prepareCall(sql);		      
//		        cs.setInt(1, book.getUserID());
//		        cs.setInt(2, book.getTableID());
//		        cs.setDate(3, book.getBookingDate());
//		        cs.setTime(4, book.getBookingTime());		   
//		        cs.registerOutParameter(5, Types.VARCHAR);		      
//		        cs.registerOutParameter(6, Types.INTEGER);
//		        cs.execute();
//		     
//		        bd.setResultMessage(cs.getString(5));
//		        bd.setBookingId(cs.getInt(6));
//		        
//		        
//		        
//		     
//		        if ("Booking_successful!".equals(bd.getResultMessage())) {		        	
//		            return bd; 
//		        } else {
//		            System.out.println(bd.getResultMessage());
//		            return bd;
//		        }
//
//		        
//		    } catch (SQLException e) {
//		        e.printStackTrace();  
//		    } finally {
//		        
//		        try {
//		            if (cs != null) cs.close();
//		            if (conn != null) conn.close();
//		        } catch (SQLException ex) {
//		            ex.printStackTrace();
//		        }
//		    } 
//		    
//		    return null; 
//		
//	}






	   @Scheduled(cron = "${cron_expression_1}")
	   public void deleteOldData() {
		  
	 	   String sql = "{Call  Rp_deleteOldData_proc }";
	 	    CallableStatement cs = null; 
		    Connection conn = null;
		    
	 	    try {		        
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	 	        cs = conn.prepareCall(sql);
	 	        cs.execute();		       
		        
	 	    } catch (SQLException e) {		       
	 	    	e.printStackTrace();  		        
	 	    } finally {
		        
	 	        try {
	 	            if (cs != null) cs.close();		            
	 	            if (conn != null) conn.close();
		            
	 	        } catch (SQLException ex) {		        	
		            ex.printStackTrace();		            
		        }
		        
	 	   }// finally ends

	 	}

     /*************************************************************************************************************************/
	 /************************************************************************************************************************/

	 public String insertBooking(BookingEntry book) {
		
	    String sql = "{ Call  Rp_bookingTable_proc( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) }"; // Stored procedure with OUTPUT parameter
	    
	    CallableStatement cs = null; // Declare as CallableStatement
	    ResultSet rs = null; // ResultSet (if needed)
	    Connection conn = null;
	    
	    try {
	    	
	    	String data = book.getBookingTime();	    	
	    	Time convertedTime = timeConverter(data);
	    	
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        cs = conn.prepareCall(sql);
	        
	        cs.setString(1, book.getName());      
	        cs.setString(2, book.getPhone_number());         
	        cs.setString(3, book.getEmail());    
	        cs.setDate(4, book.getBookingDate());
	        cs.setTime(5, convertedTime);
	        cs.setString(6, book.getCuisine_type());
	        cs.setInt(7, book.getNumberofGuest());
	        
	        cs.setString(8, book.getFoodTiming());
	        cs.setString(9, book.getTableType());
	        cs.setString(10, book.getIndoor());
	        cs.setString(11, book.getOutdoor());
	        cs.setString(12, book.getIs_private());
	        cs.setString(13, book.getEvent());
     
	        cs.registerOutParameter(14, Types.VARCHAR);
	        cs.execute();
	        
	        String resultMessage = cs.getString(14);
	        
	        if ("Success".equals(resultMessage)) {
	            return resultMessage; 
	        } else {
	            System.out.println(resultMessage);
	            return resultMessage;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();  
	    } finally {
	        
	        try {
	            if (cs != null) cs.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return null; 
	}
	
	
	 public Time timeConverter(String data) {
	  	  
		  try {
	  
			  	String timeString = data;
		        
		        // Define the input and output formats
		        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		        // Parse the input string into a LocalTime object
		        LocalTime time = LocalTime.parse(timeString, inputFormatter);
	
		        // Format the LocalTime object into the desired output format
		        String formattedTime = time.format(outputFormatter);

		        // Output the result
		        System.out.println(formattedTime); 
	  
	            // Input string
	            String s = formattedTime;

	            // Create a SimpleDateFormat to parse the string into a Date object
	            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	            // Parse the string into a Date object
	            Date parsedDate = sdf.parse(s);

	            // Convert the Date object to java.sql.Time
	            Time time1 = new Time(parsedDate.getTime());

	            // Output the result
	            System.out.println("Converted Time: " + time1);

	            return time1;
	            
	        } catch (Exception e) {
	        	
	            e.printStackTrace();
	        }		  
		    return null;		 
	  }
	

	 public Dinner getDinnerTimings() {
		 
		 String sql = " select dinnerIndoor, dinnerOutdoor, dinnerPrivate from rp_dinner "; // Stored procedure with OUTPUT parameter
		    
		    PreparedStatement ps = null;; // Declare as CallableStatement
		    ResultSet rs = null; // ResultSet (if needed)
		    Connection conn = null;
		    Dinner d = new Dinner();
		    
		    List<String> DI = new ArrayList<>();
		    List<String> DO = new ArrayList<>();
		    List<String> DP = new ArrayList<>();
		    
		    
		    try {
		    	
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
		        
		        ps = conn.prepareStatement(sql);
		        
		        rs = ps.executeQuery();
		        
		        while(rs.next()) {		        	
		        	 DI.add(rs.getString("dinnerIndoor") );
		        	 DO.add(rs.getString("dinnerOutdoor"));
	        	     DP.add(rs.getString("dinnerPrivate")); 
		        }
		        
		        d.setDinnerIndoor(DI);
		        d.setDinnerOutdoor(DO);
		        d.setDinnerPrivate(DP);
		        
		        return d;		      
		 
		    } catch (SQLException e) {
		        e.printStackTrace();  
		    } finally {
		        
		        try {
		            if (cs != null) cs.close();
		            if (conn != null) conn.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }

		    return null; 
		}


	 public Lunch getLunchTimings() {
		 
		 String sql = " select lunchIndoor, lunchOutdoor, lunchPrivate from rp_Lunch "; // Stored procedure with OUTPUT parameter
		    
		    PreparedStatement ps = null;; // Declare as CallableStatement
		    ResultSet rs = null; // ResultSet (if needed)
		    Connection conn = null;
		    Lunch l = new Lunch();
		    
		    List<String> LI = new ArrayList<>();
		    List<String> LO = new ArrayList<>();
		    List<String> LP = new ArrayList<>();
		    
		    
		    try {
		    	
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
		        
		        ps = conn.prepareStatement(sql);
		        
		        rs = ps.executeQuery();
		        
		        while(rs.next()) {		        	
		        	 LI.add(rs.getString("lunchIndoor") );
		        	 LO.add(rs.getString("lunchOutdoor"));
		        	 LP.add(rs.getString("lunchPrivate")); 
		        }
		        
		        l.setLunchIndoor(LI);;
		        l.setLunchOutdoor(LO);
		        l.setLunchPrivate(LP);
		        
		        return l;		      
		 
		    } catch (SQLException e) {
		        e.printStackTrace();  
		    } finally {
		        
		        try {
		            if (cs != null) cs.close();
		            if (conn != null) conn.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }

		    return null; 
		}


		 public List<BookingEntry> getBookingDetails() {
		 
		 String sql = " { Call Rp_GetBookingTable_proc() }"; // Stored procedure with OUTPUT parameter
		    
		    PreparedStatement ps = null;; // Declare as CallableStatement
		    ResultSet rs = null; // ResultSet (if needed)
		    Connection conn = null;
		    List<BookingEntry> ls = new ArrayList<>();
		   
		    try {
		    	
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);		        
		        ps = conn.prepareStatement(sql);		        
		        rs = ps.executeQuery();
		        
		        while(rs.next()) {		
		        	
		            BookingEntry b = new BookingEntry();
		        	
		        	b.setBookingId(rs.getInt("bookingId"));
		        	b.setName(rs.getString("name"));
		        	b.setPhone_number(rs.getString("phone_number"));
		        	b.setEmail(rs.getString("email"));
		        	b.setBookingDate(rs.getDate("bookingDate"));;
		        	b.setBookingTime(rs.getString("bookingTime"));
		        	b.setNumberofGuest(rs.getInt("NumberofGuest"));
		        	b.setCuisine_type(rs.getString("cuisine_type"));
		        	b.setTableType(rs.getString("tableType"));
		        	b.setIndoor(rs.getString("indoor"));
		        	b.setFoodTiming(rs.getString("foodTiming"));
		        	b.setEvent(rs.getString("Event"));

		            ls.add(b);
		        	
		        }
        
		        return ls;		      
		 
		    } catch (SQLException e) {
		        e.printStackTrace();  
		    } finally {
		        
		        try {
		            if (cs != null) cs.close();
		            if (conn != null) conn.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }

		    return null; 
		}
	 
 
	 public List<Map<String, Object>> fetchuser(int groupid) {
		    String sql = "{Call Access_Rp_signupTable (?)}";
		    CallableStatement cs = null;
		    ResultSet rs = null;
		    Connection conn = null;
		    List<Map<String, Object>> resultList = new ArrayList<>();

		    try {
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
		        
		        cs = conn.prepareCall(sql);
		        cs.setInt(1, groupid);
		           
		        rs = cs.executeQuery();       
		       
		        while (rs.next()) {
		            Map<String, Object> row = new HashMap<>();

		            row.put("userid", rs.getObject("userid"));
		            row.put("username", rs.getObject("username"));
		            row.put("email", rs.getObject("email"));
		            row.put("groupid", rs.getObject("groupid"));
		     
		            resultList.add(row);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace(); 
		    } finally {
		      
		        try {
		            if (rs != null) rs.close();
		            if (cs != null) cs.close();
		            if (conn != null) conn.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace(); 
		        }
		    }

		    return resultList;
		}

		public boolean updateBookingtable(BookingEntry Dinetable, int groupid) {
				String query = "{call alter_rp_booking_table( ?, ? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? )} ";

				try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
						PreparedStatement cstmt = conn.prepareCall(query)) {

					cstmt.setInt(1,Dinetable.getBookingId());
					cstmt.setString(2, Dinetable.getName());
					cstmt.setString(3, Dinetable.getPhone_number());
					cstmt.setString(4, Dinetable.getEmail());
					cstmt.setDate(5, Dinetable.getBookingDate());
					cstmt.setString(6, Dinetable.getBookingTime());
					cstmt.setString(7, Dinetable.getCuisine_type());
					cstmt.setInt(8, Dinetable.getNumberofGuest());
					
					cstmt.setString(9, Dinetable.getFoodTiming());
					cstmt.setString(10, Dinetable.getTableType());
					cstmt.setString(11, Dinetable.getIndoor());
					cstmt.setString(12, Dinetable.getOutdoor());
					cstmt.setString(13, Dinetable.getIs_private());
					cstmt.setString(14, Dinetable.getEvent());
					cstmt.setInt(15, groupid);

					int rowsUpdated = cstmt.executeUpdate();
					return rowsUpdated > 0; 

				} catch (Exception e) {
					e.printStackTrace();
				}	
				return false;	
			}
		
		
		public String deleteBookingtable(int bookingId) {
		  
			String query = "DELETE FROM Rp_bookingTable WHERE bookingId = ?";

		    String resultMessage = "Booking record not found for the given ID";

		    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		         PreparedStatement cstmt = conn.prepareStatement(query)) {
		       
		        cstmt.setInt(1, bookingId);
		      
		        int rowsAffected = cstmt.executeUpdate();
	   
		        if (rowsAffected > 0) {
		            resultMessage = "Booking record deleted successfully";
		        } else {
		            resultMessage = "No booking record found with the provided bookingId";
		        }

		    } catch (SQLException e) {
		        e.printStackTrace(); 
		        resultMessage = "An error occurred while deleting the booking record: " + e.getMessage();
		    }
		 
		    return resultMessage;
		}

		public boolean Blockuser(String username, int groupid) {
		    String sql = "{Call Block_Rp_signupTable(?, ?)}"; 
		    try {
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);

		        cs = conn.prepareCall(sql);

		        // Set input parameters for stored procedure
		        cs.setString(1, username);      
		        cs.setInt(2, groupid);        	               

		        int rowsUpdated = cs.executeUpdate();
				return rowsUpdated > 0; 
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {	        
		        try {
		            if (cs != null) cs.close();
		            if (conn != null) conn.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		    return false; 
		}

		public boolean Contact(ContactUs contact) {
			String query = "insert into rp_contact select ? , ? , ? ";

			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement ps = conn.prepareCall(query)) {

				ps.setString(1,contact.getName());
				ps.setString(2, contact.getEmail());
				ps.setString(3, contact.getMessage());
				
				int rowsUpdated = ps.executeUpdate();
				return rowsUpdated > 0; 

			} catch (Exception e) {
				e.printStackTrace();
			}	
			return false;	
		}
		
		public List<ContactUs> showAllContact() {
			
			 String sql = "select * from rp_contact"; 
			    
			    PreparedStatement ps = null;;
			    ResultSet rs = null;
			    Connection conn = null;
			    List<ContactUs> ls = new ArrayList<>();
			    
			    try {
			    	
			        conn = DriverManager.getConnection(URL, USER, PASSWORD);		        
			        ps = conn.prepareStatement(sql);		        
			        rs = ps.executeQuery();
			        
			        while(rs.next()) {		
			        	
			        	ContactUs c = new ContactUs();
			        	
			        	c.setName(rs.getString("userName"));
			        	c.setEmail(rs.getString("email"));
			        	c.setMessage(rs.getString("message"));

			            ls.add(c);
			        	
			        }
	        
			        return ls;		      
			 
			    } catch (SQLException e) {
			        e.printStackTrace();  
			    } finally {
			        
			        try {
			            if (cs != null) cs.close();
			            if (conn != null) conn.close();
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			        }
			    }

			    return null; 
			}

}