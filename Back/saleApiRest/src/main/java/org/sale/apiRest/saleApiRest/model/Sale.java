/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sale.apiRest.saleApiRest.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author AG261Y
 */
@Entity
@Table(name = "sale")
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s")
    , @NamedQuery(name = "Sale.findById", query = "SELECT s FROM Sale s WHERE s.id = :id")
    , @NamedQuery(name = "Sale.findBySubtotal", query = "SELECT s FROM Sale s WHERE s.subtotal = :subtotal")
    , @NamedQuery(name = "Sale.findByTax", query = "SELECT s FROM Sale s WHERE s.tax = :tax")
    , @NamedQuery(name = "Sale.findByTotal", query = "SELECT s FROM Sale s WHERE s.total = :total")
    , @NamedQuery(name = "Sale.findBySaleDate", query = "SELECT s FROM Sale s WHERE s.saleDate = :saleDate")
    , @NamedQuery(name = "Sale.findByCompanyId", query = "SELECT s FROM Sale s WHERE s.companyId = :companyId")
    , @NamedQuery(name = "Sale.findBySaleStatusId", query = "SELECT s FROM Sale s WHERE s.saleStatusId = :saleStatusId")})
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "subtotal")
    private String subtotal;
    @Column(name = "tax")
    private String tax;
    @Column(name = "total")
    private String total;
    @Basic(optional = false)
    @Column(name = "sale_date")
    @Temporal(TemporalType.DATE)
    private Date saleDate;
    @JsonProperty("saleDetail")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "saleId")
    private Collection<SaleDetail> saleDetailCollection;
    @Column(name = "company_id")
    private BigInteger companyId;
    @Column(name = "sale_status_id")
    private BigInteger saleStatusId;    

    public Sale() {
    }

    public Sale(Long id) {
        this.id = id;
    }

    public Sale(Long id, Date saleDate) {
        this.id = id;
        this.saleDate = saleDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Collection<SaleDetail> getSaleDetailCollection() {
        return saleDetailCollection;
    }

    public void setSaleDetailCollection(Collection<SaleDetail> saleDetailCollection) {
        this.saleDetailCollection = saleDetailCollection;
    }        

    public BigInteger getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigInteger companyId) {
		this.companyId = companyId;
	}

	public BigInteger getSaleStatusId() {
		return saleStatusId;
	}

	public void setSaleStatusId(BigInteger saleStatusId) {
		this.saleStatusId = saleStatusId;
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
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Sale[ id=" + id + " ]";
    }
    
}
