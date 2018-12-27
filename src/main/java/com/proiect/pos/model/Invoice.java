package com.proiect.pos.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @NotNull
    @Column(name = "initial_price")
    private BigDecimal initialPrice;

    @Column(name = "discounter_price")
    private BigDecimal discountedPrice;

    @JoinColumn(name = "coupon", referencedColumnName = "id")
    @ManyToOne
    private Coupon couponId;
}

