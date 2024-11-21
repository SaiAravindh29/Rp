package com.Rp.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    // Hash the password
    public static String hashPassword(String password) {
//    System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));	
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Check if the password matches the stored hash
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
