package org.sale.apiRest.saleApiRest.rest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.sale.apiRest.saleApiRest.model.DailySummary;
import org.sale.apiRest.saleApiRest.model.Sale;
import org.sale.apiRest.saleApiRest.rest.vo.Pager;
import org.sale.apiRest.saleApiRest.service.DailySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DailySummaryRest {
	
	@Autowired
	DailySummaryService service;
//-------------------Retrieve All Sale Date Range--------------------------------------------------------
    
    @RequestMapping(value = "/daily/summary/range/pager", method = RequestMethod.GET)
    public ResponseEntity<Pager<DailySummary>> listAllSaleDateRangePager(@RequestParam("page") int page, @RequestParam("size") int size
    		,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {    	
    	BigInteger companyId = new BigInteger("1");
    	Pager<DailySummary> pager = new Pager<DailySummary>();
    	pager.setPage(page);
    	pager.setSize(size);
    	Date start=null;
    	Date end=null;
    	
        try {
			start=new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
			end=new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Find by date range");
        List<DailySummary> sales = service.findAllByDateRange(companyId, pager, start, end);
        pager.setArrayFilter(sales);
        pager.setArrayLength(service.count(companyId, start, end));
        if(sales.isEmpty()){
            return new ResponseEntity<Pager<DailySummary>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Pager<DailySummary>>(pager, HttpStatus.OK);
    }
}
