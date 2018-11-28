package org.sale.apiRest.saleApiRest.service;

import java.util.List;

import org.sale.apiRest.saleApiRest.dto.WeeklySummary;
import org.sale.apiRest.saleApiRest.repository.jdbc.WeeklySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklySummaryServiceImpl implements WeeklySummaryService{

	@Autowired
	WeeklySummaryRepository repository;
	
	@Override
	public List<WeeklySummary> listWeeklySummary(String startDate, String endDate) {						
		return repository.getWeeklySummary( startDate, endDate);
	}

}
