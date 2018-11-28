package org.sale.apiRest.saleApiRest.dto;

import java.io.Serializable;

public class QueryWeeklySummary implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String startDate;	
	String endDate;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}
