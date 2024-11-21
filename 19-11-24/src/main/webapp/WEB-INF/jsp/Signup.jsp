<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/css/Signup.css">
<title>Sign Up</title>
</head>
<body>

    <div class="signup-container">
        <h2>Sign Up</h2>
        <!-- <form action="SignupServlet" method="POST"> -->
            <!-- Username Field -->
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            
            <!-- Email Field -->
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <!-- Password Field -->
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <!-- Confirm Password Field -->
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            
            <!-- Sign Up Button -->
            <div class="form-group">
                <input type="submit" value="Sign Up">
            </div>
        </form>

        <div class="form-footer">
            <p>Already have an account? <a href="login">Login here</a></p>
        </div>
    </div>

</body>
</html>