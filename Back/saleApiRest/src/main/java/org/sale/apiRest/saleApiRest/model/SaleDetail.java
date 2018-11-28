/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sale.apiRest.saleApiRest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author AG261Y
 */
@Entity
@Table(name = "sale_detail")
@NamedQueries({
    @NamedQuery(name = "SaleDetail.findAll", query = "SELECT s FROM SaleDetail s")
    , @NamedQuery(name = "SaleDetail.findBySec", query = "SELECT s FROM SaleDetail s WHERE s.sec = :sec")
    , @NamedQuery(name = "SaleDetail.findByQuantity", query = "SELECT s FROM SaleDetail s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "SaleDetail.findByPrice", query = "SELECT s FROM SaleDetail s WHERE s.price = :price")
    , @NamedQuery(name = "SaleDetail.findByTotal", query = "SELECT s FROM SaleDetail s WHERE s.total = :total")
    , @NamedQuery(name = "SaleDetail.findByInventoryId", query = "SELECT s FROM SaleDetail s WHERE s.inventoryId = :inventoryId")})
public class SaleDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sec")
    private Long sec;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "total")
    private BigDecimal total;
//    @JoinColumn(name = "sale_id", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sale_id",nullable=false, referencedColumnName = "id")
    private Sale saleId;
    @Column(name = "inventory_id")
    private Long inventoryId;

    public SaleDetail() {
    }

    public SaleDetail(Long sec) {
        this.sec = sec;
    }

    public Long getSec() {
        return sec;
    }

    public void setSec(Long sec) {
        this.sec = sec;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Sale getSaleId() {
        return saleId;
    }

    public void setSaleId(Sale saleId) {
        this.saleId = saleId;
    }        

    public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (sec != null ? sec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleDetail)) {
            return false;
        }
        SaleDetail other = (SaleDetail) object;
        if ((this.sec == null && other.sec != null) || (this.sec != null && !this.sec.equals(other.sec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SaleDetail[ sec=" + sec + " ]";
    }
    
}
