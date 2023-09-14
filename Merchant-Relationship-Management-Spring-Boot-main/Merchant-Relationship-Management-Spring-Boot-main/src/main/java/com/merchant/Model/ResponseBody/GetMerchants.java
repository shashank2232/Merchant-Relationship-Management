package com.merchant.Model.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMerchants<L extends Collection<List>> {

    private String email;

    private String phonenumber;

    private String businessname;

    private String merchanttype;

    private String isactive;
}
