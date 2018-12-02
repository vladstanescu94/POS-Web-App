//package com.proiect.pos.OLD;
//
//import java.io.Serializable;
//import java.util.Collection;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//
///**
// *
// * @author psa97
// */
//@Entity
//@Table(name = "SELLER")
//@XmlRootElement
//@NamedQueries({
//        @NamedQuery(name = "Seller.findAll", query = "SELECT s FROM Seller s")
//        , @NamedQuery(name = "Seller.findById", query = "SELECT s FROM Seller s WHERE s.id = :id")
//        , @NamedQuery(name = "Seller.findByFirstName", query = "SELECT s FROM Seller s WHERE s.firstName = :firstName")
//        , @NamedQuery(name = "Seller.findByLastName", query = "SELECT s FROM Seller s WHERE s.lastName = :lastName")
//        , @NamedQuery(name="Seller.findByPassword",query = "SELECT s FROM Seller s where s.password=:password")
//        , @NamedQuery(name = "Seller.findByIsAdmin", query = "SELECT s FROM Seller s WHERE s.isAdmin = :isAdmin")})
//public class Seller implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "ID")
//    private Long id;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 30)
//    @Column(name = "FIRST_NAME")
//    private String firstName;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 30)
//    @Column(name = "LAST_NAME")
//    private String lastName;
//    @Basic(optional=false)
//    @NotNull
//    @Size(min=5,message = "Your password must have at least 5 characters")
//    @Column(name="PASSWORD")
//    private String password;
//    @Lob
//    @Column(name = "PICTURE")
//    private Serializable picture;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "IS_ADMIN")
//    private Boolean isAdmin;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellerId")
//    private Collection<Invoice> invoiceCollection;
//
//    public Seller() {
//    }
//
//    public Seller(Long id) {
//        this.id = id;
//    }
//
//    public Seller(Long id, String firstName, String lastName, Boolean isAdmin) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.isAdmin = isAdmin;
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
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Serializable getPicture() {
//        return picture;
//    }
//
//    public void setPicture(Serializable picture) {
//        this.picture = picture;
//    }
//
//    public Boolean getIsAdmin() {
//        return isAdmin;
//    }
//
//    public void setIsAdmin(Boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }
//
//    @XmlTransient
//    public Collection<Invoice> getInvoiceCollection() {
//        return invoiceCollection;
//    }
//
//    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
//        this.invoiceCollection = invoiceCollection;
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
//        if (!(object instanceof Seller)) {
//            return false;
//        }
//        Seller other = (Seller) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.pos.Seller[ id=" + id + " ]";
//    }
//
//}
