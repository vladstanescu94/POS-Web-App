package com.proiect.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;


@Getter
@Setter
public class InvoiceItem {

    @Id
    private int id;

    private int invoiceId;
    private Integer productId;
    private int quantity;

}
