package org.sale.apiRest.saleApiRest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.sale.apiRest.saleApiRest.json.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



public class WeeklySummary implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BigDecimal total;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	Date sale_date;
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getSale_date() {
		return sale_date;
	}
	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}
	
	
}
