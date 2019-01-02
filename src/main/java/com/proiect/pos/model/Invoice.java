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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date purchaseDate = new Date();

    private BigDecimal initialPrice = BigDecimal.ZERO;

    private BigDecimal discountedPrice = BigDecimal.ZERO;

    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @OneToOne()
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToOne()
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

}
