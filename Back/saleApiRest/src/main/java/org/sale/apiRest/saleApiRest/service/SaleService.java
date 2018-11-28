package org.sale.apiRest.saleApiRest.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.sale.apiRest.saleApiRest.model.Sale;
import org.sale.apiRest.saleApiRest.rest.vo.Pager;

public interface SaleService {
	void save(Sale sale);
	void update(Sale sale);
	void deleteById(long id);
	List<Sale> findAll(BigInteger companyId);
	List<Sale> findAll(BigInteger companyId, Pager<Sale> pager);
	List<Sale> findAllByDateRange(BigInteger companyId, Pager<Sale> pager, Date startDate, Date endDate);
	Sale findById(long id);
	long count();
	long count(BigInteger companyId, Date startDate, Date endDate);	
}
