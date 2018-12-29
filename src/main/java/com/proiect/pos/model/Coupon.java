package com.proiect.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Coupon {

    @Id
    private int id;

    private String code;

    private Date expiryDate;

    private int discountPercentage;

}
