package com.merchant.Service.AdminService;

import com.merchant.Authentication.Adminlogin;
import com.merchant.Model.ResponseBody.ResponseClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminImplimentation implements AdminInterface {
    //Creating object of BCryptPasswordEncoder for match password and we need to add dependency of BCryptPasswordEncoder "spring-security-crypto".
    static BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    // static String pass=passwordEncoder.encode("abcd");
    static String userId="admin";
    static String localPass="$2a$10$vqaxDYsqJKpxFZV6xbUKVuc/84ryx6HFph0chDjdiJvcPeEpms3bK";

    public ResponseEntity<ResponseClass> adminAuthentication(Adminlogin adminlogin) {

        ResponseClass responseClass=new ResponseClass();

        if(userId.equals(adminlogin.getAdminId())){
            if(passwordEncoder.matches(adminlogin.getAdminPass(),localPass)){
                responseClass.setResponseMessage("Admin Login Successfully");
                return ResponseEntity.status(HttpStatus.OK).body(responseClass);
            }else{
                responseClass.setResponseMessage("Password is Incorrect");
            }
        }else {
            responseClass.setResponseMessage("Please check your admin ID");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseClass);
    }

}
