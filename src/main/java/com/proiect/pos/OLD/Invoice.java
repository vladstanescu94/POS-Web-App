//package com.proiect.pos.OLD;
//
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Collection;
//import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//
///**
// *
// * @author psa97
// */
//@Entity
//@Table(name = "INVOICE")
//@XmlRootElement
//@NamedQueries({
//        @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
//        , @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id")
//        , @NamedQuery(name = "Invoice.findByPurchaseDate", query = "SELECT i FROM Invoice i WHERE i.purchaseDate = :purchaseDate")
//        , @NamedQuery(name = "Invoice.findByInitialPrice", query = "SELECT i FROM Invoice i WHERE i.initialPrice = :initialPrice")
//        , @NamedQuery(name = "Invoice.findByDiscountedPrice", query = "SELECT i FROM Invoice i WHERE i.discountedPrice = :discountedPrice")})
//public class Invoice implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "ID")
//    private Long id;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "PURCHASE_DATE")
//    @Temporal(TemporalType.DATE)
//    private Date purchaseDate;
//    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "INITIAL_PRICE")
//    private BigDecimal initialPrice;
//    @Column(name = "DISCOUNTED_PRICE")
//    private BigDecimal discountedPrice;
//    @JoinColumn(name = "COUPON_ID", referencedColumnName = "ID")
//    @ManyToOne
//    private Coupon couponId;
//    @JoinColumn(name = "SELLER_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Seller sellerId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
//    private Collection<InvoiceItems> invoiceItemsCollection;
//
//    public Invoice() {
//    }
//
//    public Invoice(Long id) {
//        this.id = id;
//    }
//
//    public Invoice(Long id, Date purchaseDate, BigDecimal initialPrice) {
//        this.id = id;
//        this.purchaseDate = purchaseDate;
//        this.initialPrice = initialPrice;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Date getPurchaseDate() {
//        return purchaseDate;
//    }
//
//    public void setPurchaseDate(Date purchaseDate) {
//        this.purchaseDate = purchaseDate;
//    }
//
//    public BigDecimal getInitialPrice() {
//        return initialPrice;
//    }
//
//    public void setInitialPrice(BigDecimal initialPrice) {
//        this.initialPrice = initialPrice;
//    }
//
//    public BigDecimal getDiscountedPrice() {
//        return discountedPrice;
//    }
//
//    public void setDiscountedPrice(BigDecimal discountedPrice) {
//        this.discountedPrice = discountedPrice;
//    }
//
//    public Coupon getCouponId() {
//        return couponId;
//    }
//
//    public void setCouponId(Coupon couponId) {
//        this.couponId = couponId;
//    }
//
//    public Seller getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(Seller sellerId) {
//        this.sellerId = sellerId;
//    }
//
//    @XmlTransient
//    public Collection<InvoiceItems> getInvoiceItemsCollection() {
//        return invoiceItemsCollection;
//    }
//
//    public void setInvoiceItemsCollection(Collection<InvoiceItems> invoiceItemsCollection) {
//        this.invoiceItemsCollection = invoiceItemsCollection;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Invoice)) {
//            return false;
//        }
//        Invoice other = (Invoice) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.pos.Invoice[ id=" + id + " ]";
//    }
//
//}