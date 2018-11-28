package org.sale.apiRest.saleApiRest.repository.jpa;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.sale.apiRest.saleApiRest.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {
	List<Sale> findByCompanyIdOrderBySaleDateDesc(BigInteger companyId);
	Page<Sale> findByCompanyIdOrderBySaleDateDesc(BigInteger companyId, Pageable pageable);
	Page<Sale> findByCompanyIdAndSaleDateBetween(BigInteger companyId,Date start, Date end, Pageable pageable);
	long count();
	long countByCompanyIdAndSaleDateBetween(BigInteger companyId,Date start, Date end);
}
