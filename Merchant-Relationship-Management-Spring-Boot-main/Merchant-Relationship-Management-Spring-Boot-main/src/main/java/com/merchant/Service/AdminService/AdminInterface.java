package com.merchant.Service.AdminService;

import com.merchant.Authentication.Adminlogin;
import com.merchant.Model.ResponseBody.ResponseClass;
import org.springframework.http.ResponseEntity;

public interface AdminInterface {

    public ResponseEntity<ResponseClass> adminAuthentication(Adminlogin adminlogin);
}
