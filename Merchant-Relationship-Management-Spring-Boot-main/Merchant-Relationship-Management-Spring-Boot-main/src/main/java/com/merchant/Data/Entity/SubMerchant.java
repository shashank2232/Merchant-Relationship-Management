package com.merchant.Data.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "submerchant")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubMerchant {

    @Id
    private String uniqueid;

    @Column(nullable = false)
    private String password ;

    @Column(nullable = false)
    private String email ;

    @Column(nullable = false)
    private String phonenumber ;

    @Column(nullable = false)
    private String businessname ;

    @Column(nullable = false)
    private String merchanttype ;

    @Column(nullable = false)
    private Boolean isdelete ;

    @Column(nullable = false)
    private String isactive;

}
