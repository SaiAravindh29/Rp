<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/css/Login.css">
<title>Sign Up</title>
</head>
<body>
    <h2>Login Form</h2>
    <!-- <form action="LoginServlet" method="post"> -->
    <form >
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required/><br/><br/>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required/><br/><br/>
        
        <button type="submit">Submit</button>
    </form>
    
</body>
</html>