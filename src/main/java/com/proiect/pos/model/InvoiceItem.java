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

    @Column(name="invoice_id")
    private int invoiceId;

    @Column(name="product_id")
    private Integer productId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="invoice_id", insertable = false, updatable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;


}
