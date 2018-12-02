package com.proiect.pos.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="product")
@Getter
@Setter
@Data
public class Product {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id" )
    private int id;

    @Column(name="name")
    @NotEmpty(message = "Please provide the name of the product")
    private String name;

    @Column(name="stock")
    @NotNull(message="Please provide the stock available")
    @Min(1)
    private int stock;

    @Column(name="price")
    @NotNull(message="Please provide the price of the product")
    @DecimalMin("0.01")
    private double price;

    @Column(name="image")
    private Serializable image;
}
