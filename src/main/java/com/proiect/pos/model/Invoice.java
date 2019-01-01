package com.proiect.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
public class Invoice {

    @Id
    private long id;

    private Date purchaseDate;

    private int sellerId;

//    @OneToOne
//    @JoinColumn(name="seller_id",insertable = false, updatable = false)
//    private Seller seller;

    private int couponId;

//    @OneToOne
//    @JoinColumn(name="coupon_id",insertable = false, updatable = false)
//    private Coupon coupon;

    private BigDecimal initialPrice = BigDecimal.ZERO;

    private BigDecimal discountedPrice = BigDecimal.ZERO;

    @Transient
    List<InvoiceItem> invoiceItems=new ArrayList<>();

    @Transient
    Map<Integer,Integer> shoppingCart =new HashMap<>();

//    @ManyToMany
//    private Set<InvoiceItem> items;

}
