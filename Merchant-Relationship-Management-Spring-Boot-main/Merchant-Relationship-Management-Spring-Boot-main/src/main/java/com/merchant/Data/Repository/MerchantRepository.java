package com.merchant.Data.Repository;

import com.merchant.Data.Entity.Merchant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

    @Query(nativeQuery = true, value = "select * from merchant where businessname=:name")
    Merchant existBybusinesName(@Param("name") String name);

    @Query(nativeQuery = true, value = "select * from merchant where email=:name")
    Merchant existEmailId(@Param("name") String name);

    @Query(nativeQuery = true, value = " select * from merchant where uniqueid=:id")
    Merchant findWithId(String id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value ="UPDATE merchant set isdelete=true where email= :email")
    public void deleteWithEmail(@Param("email") String email);
}
