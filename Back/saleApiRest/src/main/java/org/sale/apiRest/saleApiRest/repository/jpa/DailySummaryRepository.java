package org.sale.apiRest.saleApiRest.repository.jpa;

import java.math.BigInteger;
import java.util.Date;

import org.sale.apiRest.saleApiRest.model.DailySummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DailySummaryRepository extends PagingAndSortingRepository<DailySummary, Date>{
	Page<DailySummary> findByCompanyIdAndSaleDateBetween(BigInteger companyId, Date start, Date end, Pageable pageable);
	long countByCompanyIdAndSaleDateBetween(BigInteger companyId, Date start, Date end);
}
