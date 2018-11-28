package org.sale.apiRest.saleApiRest.service;

import java.util.List;

import org.sale.apiRest.saleApiRest.dto.WeeklySummary;

public interface WeeklySummaryService {
	List<WeeklySummary> listWeeklySummary(String startDate,String endDate);
}
