package org.sale.apiRest.saleApiRest.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.sale.apiRest.saleApiRest.model.DailySummary;

import org.sale.apiRest.saleApiRest.rest.vo.Pager;

public interface DailySummaryService {
	List<DailySummary> findAllByDateRange(BigInteger companyId, Pager<DailySummary> pager, Date startDate, Date endDate);
	long count(BigInteger companyId, Date startDate, Date endDate);
}
