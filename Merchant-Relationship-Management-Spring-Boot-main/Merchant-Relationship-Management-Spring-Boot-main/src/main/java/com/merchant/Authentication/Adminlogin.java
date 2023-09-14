package com.merchant.Authentication;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adminlogin {

    @NotEmpty
    @NotNull
    private String adminId;

    @NotEmpty
    @NotNull
    private String adminPass;
}
