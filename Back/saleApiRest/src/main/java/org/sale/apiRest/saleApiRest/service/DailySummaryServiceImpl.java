package org.sale.apiRest.saleApiRest.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.sale.apiRest.saleApiRest.model.DailySummary;
import org.sale.apiRest.saleApiRest.model.Sale;
import org.sale.apiRest.saleApiRest.repository.jpa.DailySummaryRepository;
import org.sale.apiRest.saleApiRest.rest.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DailySummaryServiceImpl implements DailySummaryService{

	@Autowired
	DailySummaryRepository repository;
	
	@Override
	public List<DailySummary> findAllByDateRange(BigInteger companyId, Pager<DailySummary> pager, Date startDate, Date endDate) {
		Pageable pageable = PageRequest.of(pager.getPage(), pager.getSize());
		Page<DailySummary> page = repository.findByCompanyIdAndSaleDateBetween(companyId, startDate, endDate, pageable);		
		return page.getContent();
	}

	@Override
	public long count(BigInteger companyId, Date startDate, Date endDate) {
		return repository.countByCompanyIdAndSaleDateBetween(companyId,startDate, endDate);
	}

}
