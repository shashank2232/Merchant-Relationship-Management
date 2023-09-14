package com.merchant.Model.RequestBody;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Parameter;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRequest {

    @NotEmpty(message = "Email dose not Empty")
    @NotNull(message = "Email not null")
    @Email
    @Pattern(regexp = "^([a-z0-9_\\.-]+\\@[\\da-z\\.-]+\\.[a-z\\.]{2,6})$", message = "Check Email, it is not Appropriate")
    private String email;

    @NotEmpty(message = "Phone Number dose not Empty")
    @NotNull(message = "Phone Number  not Null")
    @Size(min = 10, message = "Phone Number at least 10 digit")
    @Size(max = 13, message = "Phone Number at most 13 digit")
    @Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$", message = "Check Phone Number, it is not Appropriate")
    private String phonenumber;

    @NotEmpty(message = "Business Name dose not Empty")
    @NotNull(message = "Business Name not Null")
    private String businessname;

    @NotEmpty(message = "Merchant Type dose not Empty")
    @NotNull(message = "Merchant Type not Null")
    private String merchanttype;
}
