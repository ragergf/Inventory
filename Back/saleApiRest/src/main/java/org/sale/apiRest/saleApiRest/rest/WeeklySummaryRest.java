package org.sale.apiRest.saleApiRest.rest;

import java.math.BigInteger;
import java.util.List;

import org.sale.apiRest.saleApiRest.dto.WeeklySummary;
import org.sale.apiRest.saleApiRest.model.Sale;
import org.sale.apiRest.saleApiRest.rest.vo.Pager;
import org.sale.apiRest.saleApiRest.service.WeeklySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeeklySummaryRest {
	@Autowired
	WeeklySummaryService weeklySummaryService;
	
//-------------------Retrieve All Inventory--------------------------------------------------------
    
    @RequestMapping(value = "/weekly/summary", method = RequestMethod.GET)
    public ResponseEntity<List<WeeklySummary>> listAllUsersPager(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {    	
    	    	
    	List<WeeklySummary> list = weeklySummaryService.listWeeklySummary(startDate, endDate);
        if(list.isEmpty()){
            return new ResponseEntity<List<WeeklySummary>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<WeeklySummary>>(list, HttpStatus.OK);
    }
}
