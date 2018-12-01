package com.proiect.pos.Entities;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author psa97
 */
@Entity
@Table(name = "COUPON")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Coupon.findAll", query = "SELECT c FROM Coupon c")
        , @NamedQuery(name = "Coupon.findById", query = "SELECT c FROM Coupon c WHERE c.id = :id")
        , @NamedQuery(name = "Coupon.findByCode", query = "SELECT c FROM Coupon c WHERE c.code = :code")
        , @NamedQuery(name = "Coupon.findByQuantityIssued", query = "SELECT c FROM Coupon c WHERE c.quantityIssued = :quantityIssued")
        , @NamedQuery(name = "Coupon.findByQuantityUsed", query = "SELECT c FROM Coupon c WHERE c.quantityUsed = :quantityUsed")
        , @NamedQuery(name = "Coupon.findByDiscountPercentage", query = "SELECT c FROM Coupon c WHERE c.discountPercentage = :discountPercentage")})
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY_ISSUED")
    private int quantityIssued;
    @Column(name = "QUANTITY_USED")
    private Integer quantityUsed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISCOUNT_PERCENTAGE")
    private int discountPercentage;
    @OneToMany(mappedBy = "couponId")
    private Collection<Invoice> invoiceCollection;

    public Coupon() {
    }

    public Coupon(Long id) {
        this.id = id;
    }

    public Coupon(Long id, String code, int quantityIssued, int discountPercentage) {
        this.id = id;
        this.code = code;
        this.quantityIssued = quantityIssued;
        this.discountPercentage = discountPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantityIssued() {
        return quantityIssued;
    }

    public void setQuantityIssued(int quantityIssued) {
        this.quantityIssued = quantityIssued;
    }

    public Integer getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(Integer quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coupon)) {
            return false;
        }
        Coupon other = (Coupon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pos.Coupon[ id=" + id + " ]";
    }

}