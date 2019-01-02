package com.proiect.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @NotNull(message = "Please provide the barcode of the product")
    private int id;

    @NotEmpty(message = "Please provide the name of the product")
    private String name;

    @NotNull(message = "Please provide the stock available")
    @Min(0)
    private int stock;

//    @NotEmpty(message = "Please provide the price of the product")
    @DecimalMin("0.01")
    private BigDecimal price;

    private String image = "-1";
}
