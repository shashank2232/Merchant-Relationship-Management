package com.merchant.Data.Repository;

import com.merchant.Data.Entity.Merchant;
import com.merchant.Data.Entity.SubMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubMerchantRepository extends JpaRepository<SubMerchant,String> {

    @Query(nativeQuery = true,value = "select * from submerchant where email=:name")
    SubMerchant existEmailId(@Param("name") String name);

    @Query(nativeQuery = true,value =" select email, phonenumber,businessname,merchanttype,isactive from submerchant where businessname=:name")
    List findWithBusinessName(@Param("name") String name);
}
