/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author psa97
 */
@Entity
@Table(name = "INVOICE_ITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoiceItems.findAll", query = "SELECT i FROM InvoiceItems i")
    , @NamedQuery(name = "InvoiceItems.findByInvoiceId", query = "SELECT i FROM InvoiceItems i WHERE i.invoiceItemsPK.invoiceId = :invoiceId")
    , @NamedQuery(name = "InvoiceItems.findByProductId", query = "SELECT i FROM InvoiceItems i WHERE i.invoiceItemsPK.productId = :productId")
    , @NamedQuery(name = "InvoiceItems.findByQuantity", query = "SELECT i FROM InvoiceItems i WHERE i.quantity = :quantity")})
public class InvoiceItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvoiceItemsPK invoiceItemsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Invoice invoice;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public InvoiceItems() {
    }

    public InvoiceItems(InvoiceItemsPK invoiceItemsPK) {
        this.invoiceItemsPK = invoiceItemsPK;
    }

    public InvoiceItems(InvoiceItemsPK invoiceItemsPK, int quantity) {
        this.invoiceItemsPK = invoiceItemsPK;
        this.quantity = quantity;
    }

    public InvoiceItems(long invoiceId, long productId) {
        this.invoiceItemsPK = new InvoiceItemsPK(invoiceId, productId);
    }

    public InvoiceItemsPK getInvoiceItemsPK() {
        return invoiceItemsPK;
    }

    public void setInvoiceItemsPK(InvoiceItemsPK invoiceItemsPK) {
        this.invoiceItemsPK = invoiceItemsPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceItemsPK != null ? invoiceItemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceItems)) {
            return false;
        }
        InvoiceItems other = (InvoiceItems) object;
        if ((this.invoiceItemsPK == null && other.invoiceItemsPK != null) || (this.invoiceItemsPK != null && !this.invoiceItemsPK.equals(other.invoiceItemsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pos.InvoiceItems[ invoiceItemsPK=" + invoiceItemsPK + " ]";
    }
    
}
