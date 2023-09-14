package com.merchant.Utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    public String genrateId(){
        String uniqeid1= RandomStringUtils.randomAlphabetic(3,3);
        String uniqeid2=RandomStringUtils.randomNumeric(3,3);
        String uniqeid=uniqeid1+uniqeid2;
        return uniqeid;
    }

    public String genratePassword(){
        String password=RandomStringUtils.randomNumeric(6,6);
        return password;
    }

    public boolean checkBusiness(String name){
        List<String> BusinessList= Arrays.asList("milk","water","grocery","electronics","clothing");
        boolean value=BusinessList.contains(name);
        return value;
    }
    public boolean checkSeller(String name){
        List<String> BusinessList= Arrays.asList("wholesaler","seller");
        boolean value=BusinessList.contains(name);
        return value;
    }


}
