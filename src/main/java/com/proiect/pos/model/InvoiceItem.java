package com.proiect.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@Entity
public class InvoiceItem {

    @Id
    private int id;
    
    private int quantity;

    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;


}
