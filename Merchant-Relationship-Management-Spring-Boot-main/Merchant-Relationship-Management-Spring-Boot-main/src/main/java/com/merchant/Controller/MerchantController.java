package com.merchant.Controller;


import com.merchant.Authentication.MerchantLogin;
import com.merchant.Data.Entity.Merchant;
import com.merchant.Model.RequestBody.MerchantRequest;
import com.merchant.Model.ResponseBody.MerchantResponse;
import com.merchant.Service.MerchantService.ServiceImplimentation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController {

    @Autowired
    ServiceImplimentation merchantService;

    @PostMapping
    @RequestMapping("save")
    public ResponseEntity<MerchantResponse> saveMerchant(@Valid @RequestBody MerchantRequest merchantRequest) {
        return merchantService.addMerchant(merchantRequest);
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity loginMerchant(@Valid @RequestBody MerchantLogin merchantLogin)  {
        return merchantService.loginMerchant(merchantLogin);
    }

    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<List> getMerchants(@Valid @RequestParam() String businessName) {
        return merchantService.getMerchant(businessName);
    }
    @DeleteMapping
    @RequestMapping("/delete{email}")
    public ResponseEntity deleteMerchant(@RequestParam() String email){
        return merchantService.deleteByEmail(email);
    }
}
