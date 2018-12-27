package com.proiect.pos.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@Data

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull(message = "Please provide the code of the coupon")
    @Column(name = "code")
    private String code;

    @NotNull(message = "Please provide how many coupons were issued")
    @Column(name = "quantity_issued")
    @Min(1)
    private int quantityIssued;

    @NotNull(message = "Please provide how many coupons were used")
    @Column(name = "quantity_used")
    private int quantityUsed;

    @NotNull(message = "Please provide the discount percentage")
    @Column(name = "discount_percentage")
    private float discountPercentage;
}
