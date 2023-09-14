package com.merchant.Model.ResponseBody;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantResponse {

    private String Massage;

    private String UniqueID;

    private String Password;
}
