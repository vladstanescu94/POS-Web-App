//package com.proiect.pos.OLD;
//
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.validation.constraints.NotNull;
//
///**
// *
// * @author psa97
// */
//@Embeddable
//public class InvoiceItemsPK implements Serializable {
//
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "INVOICE_ID")
//    private long invoiceId;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "PRODUCT_ID")
//    private long productId;
//
//    public InvoiceItemsPK() {
//    }
//
//    public InvoiceItemsPK(long invoiceId, long productId) {
//        this.invoiceId = invoiceId;
//        this.productId = productId;
//    }
//
//    public long getInvoiceId() {
//        return invoiceId;
//    }
//
//    public void setInvoiceId(long invoiceId) {
//        this.invoiceId = invoiceId;
//    }
//
//    public long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(long productId) {
//        this.productId = productId;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (int) invoiceId;
//        hash += (int) productId;
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof InvoiceItemsPK)) {
//            return false;
//        }
//        InvoiceItemsPK other = (InvoiceItemsPK) object;
//        if (this.invoiceId != other.invoiceId) {
//            return false;
//        }
//        if (this.productId != other.productId) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.pos.InvoiceItemsPK[ invoiceId=" + invoiceId + ", productId=" + productId + " ]";
//    }
//
//}