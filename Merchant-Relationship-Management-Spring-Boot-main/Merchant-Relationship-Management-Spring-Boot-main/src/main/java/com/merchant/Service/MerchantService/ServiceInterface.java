package com.merchant.Service.MerchantService;

import com.merchant.Authentication.MerchantLogin;
import com.merchant.Model.RequestBody.MerchantRequest;
import com.merchant.Model.ResponseBody.MerchantResponse;
import com.merchant.Model.ResponseBody.ResponseClass;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceInterface {
    ResponseEntity<MerchantResponse> addMerchant(MerchantRequest merchantRequest);

    ResponseEntity<List> loginMerchant(MerchantLogin merchantLogin);

    ResponseEntity<List> getMerchant(String  bussnesName);

    ResponseEntity deleteByEmail(String email);
}
