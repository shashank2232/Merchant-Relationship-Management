package com.merchant.Authentication;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantLogin {

    @NotEmpty
    @NotNull
    private String id;

    @NotEmpty
    @NotNull
    private String password;

}
