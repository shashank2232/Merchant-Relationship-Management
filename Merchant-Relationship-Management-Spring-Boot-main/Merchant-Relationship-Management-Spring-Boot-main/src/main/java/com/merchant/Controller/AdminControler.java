package com.merchant.Controller;

import com.merchant.Authentication.Adminlogin;
import com.merchant.Model.ResponseBody.ResponseClass;
import com.merchant.Service.AdminService.AdminImplimentation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminControler {

    @Autowired
    private AdminImplimentation adminService;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<ResponseClass> adminLogin(@Valid @RequestBody Adminlogin adminlogin) {
        return adminService.adminAuthentication(adminlogin);
    }

}
