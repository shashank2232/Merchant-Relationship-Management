package com.merchant.Service.MerchantService;


import com.merchant.Authentication.MerchantLogin;
import com.merchant.Data.Entity.Merchant;
import com.merchant.Data.Entity.SubMerchant;
import com.merchant.Data.Repository.MerchantRepository;
import com.merchant.Data.Repository.SubMerchantRepository;
import com.merchant.Model.RequestBody.MerchantRequest;
import com.merchant.Model.ResponseBody.GetMerchants;
import com.merchant.Model.ResponseBody.MerchantResponse;
import com.merchant.Model.ResponseBody.ResponseClass;
import com.merchant.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceImplimentation implements ServiceInterface {
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private SubMerchantRepository subMerchantRepository;

    //Genrate id and password in Utils Class
    Utils utils = new Utils();

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<MerchantResponse> addMerchant(MerchantRequest merchantRequest) {
        Merchant merchant = new Merchant();
        SubMerchant subMerchant = new SubMerchant();
        MerchantResponse merchantResponse = new MerchantResponse();

        merchant.setUniqueid(utils.genrateId());

        String password = utils.genratePassword();
        String pass = passwordEncoder.encode(password);
        merchant.setPassword(pass);

        merchant.setEmail(merchantRequest.getEmail());

        //Check Business Name is correct or not
        if (utils.checkBusiness(merchantRequest.getBusinessname())) {
            merchant.setBusinessname(merchantRequest.getBusinessname());
        } else {
            merchantResponse.setMassage("Business Name Incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(merchantResponse);
        }

        merchant.setPhonenumber(merchantRequest.getPhonenumber());

        //Check Merchant Type is correct or not
        if (utils.checkSeller(merchantRequest.getMerchanttype())) {
            merchant.setMerchanttype(merchantRequest.getMerchanttype());

        } else {

            merchantResponse.setMassage("Check Merchant Type only wholesaler or seller");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(merchantResponse);
        }

        merchant.setIsdelete(false);

        merchant.setIsactive("Active");
        //wholesaler signUp code
        if (merchant.getMerchanttype().equalsIgnoreCase("wholesaler")) {
            if (merchantRepository.existBybusinesName(merchant.getBusinessname()) == null) {

                if (merchantRepository.existEmailId(merchantRequest.getEmail()) == null) {
                    merchantRepository.save(merchant);
                } else {
                    merchantResponse.setMassage("Email Id is already exist");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(merchantResponse);
                }
            } else {
                merchantResponse.setMassage("This Business Type is already exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(merchantResponse);
            }
        }
        //Seller Signup code
        else {
            if (subMerchantRepository.existEmailId(merchantRequest.getEmail()) == null) {
                subMerchant.setUniqueid(utils.genrateId());
                subMerchant.setPassword(pass);
                subMerchant.setEmail(merchantRequest.getEmail());
                subMerchant.setBusinessname(merchantRequest.getBusinessname());
                subMerchant.setMerchanttype(merchantRequest.getMerchanttype());
                subMerchant.setPhonenumber(merchantRequest.getPhonenumber());
                subMerchant.setIsdelete(false);
                subMerchant.setIsactive("Active");
                subMerchantRepository.save(subMerchant);
                merchantResponse.setMassage("SignUp Successfully Completed");
                merchantResponse.setUniqueID(subMerchant.getUniqueid());
                merchantResponse.setPassword(password);
                return ResponseEntity.status(HttpStatus.CREATED).body(merchantResponse);
            } else {
                merchantResponse.setMassage("Email Id is already exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(merchantResponse);
            }
        }
        merchantResponse.setMassage("SignUp merchant Successfully ");
        merchantResponse.setUniqueID(merchant.getUniqueid());
        merchantResponse.setPassword(password);
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantResponse);
    }

    @Override
    public ResponseEntity loginMerchant(MerchantLogin merchantLogin) {
        ResponseClass responseClass = new ResponseClass();
        String id = merchantLogin.getId();
        Merchant merchant = merchantRepository.findWithId(id);
        if (merchant != null) {
            if (passwordEncoder.matches(merchantLogin.getPassword(), merchant.getPassword())) {
                //Get all Sub-Merchant of same Business type
                String businessName = merchant.getBusinessname();

               // List<GetMerchants> merchantsList = new ArrayList();

              List<GetMerchants> list = subMerchantRepository.findWithBusinessName(businessName);

     //           for ( GetMerchants gtm: list) {
//                    GetMerchants getMerchants = new GetMerchants();
//                    getMerchants.setBusinessname(gtm.getBusinessname());
//                    getMerchants.setEmail(gtm.getEmail());
//                    getMerchants.setPhonenumber(gtm.getPhonenumber());
//                    getMerchants.setMerchanttype(gtm.getMerchanttype());
//                    getMerchants.setIsactive(gtm.getIsactive());
//                    merchantsList.add(getMerchants);
//
//           }
                return ResponseEntity.status(HttpStatus.OK).body(list);
            } else {
                responseClass.setResponseMessage("Password is not Match");
                return ResponseEntity.status(HttpStatus.OK).body(responseClass);
            }
        }
        responseClass.setResponseMessage("Id is not Match");
        return ResponseEntity.status(HttpStatus.OK).body(responseClass);
    }

    @Override
    public ResponseEntity getMerchant(String businessName) {
        System.out.println("Name Business= " + businessName);
        if (businessName == "") {
            List list = merchantRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(list);

        } else {
            Merchant data = merchantRepository.existBybusinesName(businessName);
            if (data == null) {
                ResponseClass responseClass = new ResponseClass();
                responseClass.setResponseMessage("Data dose not exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseClass);
            }
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }

    }

    @Override
    public ResponseEntity deleteByEmail(String email) {
        Merchant list = merchantRepository.existEmailId(email);
        ResponseClass responseClass = new ResponseClass();
        if (list != null) {
            merchantRepository.deleteWithEmail(email);

            responseClass.setResponseMessage("Merchant Deleted Successfully ");
            return ResponseEntity.status(HttpStatus.OK).body(responseClass);
        }
        responseClass.setResponseMessage("Merchant Dose Not Exist");
        return ResponseEntity.status(HttpStatus.OK).body(responseClass);
    }

}
