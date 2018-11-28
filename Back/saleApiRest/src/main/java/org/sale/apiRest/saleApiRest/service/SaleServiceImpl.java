package org.sale.apiRest.saleApiRest.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sale.apiRest.saleApiRest.model.Sale;
import org.sale.apiRest.saleApiRest.model.SaleDetail;
import org.sale.apiRest.saleApiRest.repository.jpa.SaleRepository;
import org.sale.apiRest.saleApiRest.rest.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("saleService")
@Transactional
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	SaleRepository saleRepository;

	@Override
	public void save(Sale sale) { 
		sale.setSaleDate(new Date());
		sale.setCompanyId(new BigInteger("1"));
		sale.setSaleStatusId(new BigInteger("1"));
				
		for(SaleDetail detail : sale.getSaleDetailCollection())
		{
			detail.setSaleId(sale);
		}				

		saleRepository.save(sale);
	}

	@Override
	public void update(Sale sale) {
		saleRepository.save(sale);
	}

	@Override
	public void deleteById(long id) {
		
	}

	@Override
	public List<Sale> findAll(BigInteger companyId) {
		return (List<Sale>) saleRepository.findByCompanyIdOrderBySaleDateDesc(companyId);
	}

	@Override
	public Sale findById(long id) {
		return saleRepository.findById(id).get();
	}

	@Override
	public List<Sale> findAll(BigInteger companyId, Pager<Sale> pager) {
		Pageable pageable = PageRequest.of(pager.getPage(), pager.getSize());
		Page<Sale> page = saleRepository.findByCompanyIdOrderBySaleDateDesc(companyId, pageable);
		return page.getContent();
	}
	
	@Override
	public List<Sale> findAllByDateRange(BigInteger companyId, Pager<Sale> pager, Date startDate, Date endDate) {
		Pageable pageable = PageRequest.of(pager.getPage(), pager.getSize());
		Page<Sale> page = saleRepository.findByCompanyIdAndSaleDateBetween(companyId, startDate, endDate, pageable);		
		return page.getContent();
	}

	@Override
	public long count() {
		return saleRepository.count();
	}
	
	@Override
	public long count(BigInteger companyId, Date startDate, Date endDate) {
		return saleRepository.countByCompanyIdAndSaleDateBetween(companyId, startDate, endDate);
	}
}
