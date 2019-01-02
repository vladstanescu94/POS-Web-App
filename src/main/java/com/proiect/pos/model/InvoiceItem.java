package com.proiect.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;


}
