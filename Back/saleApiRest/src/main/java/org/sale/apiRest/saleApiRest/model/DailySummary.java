package org.sale.apiRest.saleApiRest.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AG261Y
 */
@Entity
@Table(name = "daily_summary")
@NamedQueries({
    @NamedQuery(name = "DailySummary.findAll", query = "SELECT d FROM DailySummary d")
    , @NamedQuery(name = "DailySummary.findBySaleDate", query = "SELECT d FROM DailySummary d WHERE d.saleDate = :saleDate")
    , @NamedQuery(name = "DailySummary.findByTotal", query = "SELECT d FROM DailySummary d WHERE d.total = :total")
    , @NamedQuery(name = "DailySummary.findByCompanyId", query = "SELECT d FROM DailySummary d WHERE d.companyId = :companyId")})
public class DailySummary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sale_date")
    @Temporal(TemporalType.DATE)
    private Date saleDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Basic(optional = false)
    @Column(name = "company_id")
    private BigInteger companyId;    

    public DailySummary() {
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public BigInteger getCompanyId() {
        return companyId;
    }

    public void setCompanyId(BigInteger companyId) {
        this.companyId = companyId;
    }
}
