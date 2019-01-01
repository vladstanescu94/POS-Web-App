package com.proiect.pos.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

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

    @Column(name="seller_id")
    private int sellerId;

    @Column(name="coupon_id")
    private int couponId;

    private BigDecimal initialPrice = BigDecimal.ZERO;

    private BigDecimal discountedPrice = BigDecimal.ZERO;


    //TODO REMOVE THIS
    @Transient
    Map<Integer,Integer> shoppingCart=new HashMap<>();


    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<InvoiceItem> invoiceItems=new ArrayList<>();

    @OneToOne
    @JoinColumn(name="seller_id", insertable = false, updatable = false)
    private Seller seller;

    @OneToOne
    @JoinColumn(name="coupon_id",insertable = false,updatable = false)
    private Coupon coupon;


}
