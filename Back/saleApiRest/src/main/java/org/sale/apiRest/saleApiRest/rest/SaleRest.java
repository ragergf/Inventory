package org.sale.apiRest.saleApiRest.rest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.sale.apiRest.saleApiRest.model.Sale;
import org.sale.apiRest.saleApiRest.rest.vo.Pager;
import org.sale.apiRest.saleApiRest.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SaleRest {
	
	@Autowired
	SaleService saleService;
	
//-------------------Retrieve All Inventory--------------------------------------------------------
    
    @RequestMapping(value = "/sale/", method = RequestMethod.GET)
    public ResponseEntity<List<Sale>> listAllUsers() {
    	BigInteger companyId = new BigInteger("1");
        List<Sale> users = saleService.findAll(companyId);
        if(users.isEmpty()){
            return new ResponseEntity<List<Sale>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Sale>>(users, HttpStatus.OK);
    }
    
//-------------------Retrieve All Inventory--------------------------------------------------------
    
    @RequestMapping(value = "/sale/pager", method = RequestMethod.GET)
    public ResponseEntity<Pager<Sale>> listAllUsersPager(@RequestParam("page") int page,@RequestParam("size") int size) {    	
    	BigInteger companyId = new BigInteger("1");
    	Pager<Sale> pager = new Pager<Sale>();
    	pager.setPage(page);
    	pager.setSize(size);
        List<Sale> sales = saleService.findAll(companyId, pager);
        pager.setArrayFilter(sales);
        pager.setArrayLength(saleService.count());
        if(sales.isEmpty()){
            return new ResponseEntity<Pager<Sale>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Pager<Sale>>(pager, HttpStatus.OK);
    }
    
    
//-------------------Retrieve All Sale Date Range--------------------------------------------------------
    
    @RequestMapping(value = "/sale/range/pager", method = RequestMethod.GET)
    public ResponseEntity<Pager<Sale>> listAllSaleDateRangePager(@RequestParam("page") int page, @RequestParam("size") int size
    		,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {    	
    	BigInteger companyId = new BigInteger("1");
    	Pager<Sale> pager = new Pager<Sale>();
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
        List<Sale> sales = saleService.findAllByDateRange(companyId, pager, start, end);
        pager.setArrayFilter(sales);
        pager.setArrayLength(saleService.count(companyId, start, end));
        if(sales.isEmpty()){
            return new ResponseEntity<Pager<Sale>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Pager<Sale>>(pager, HttpStatus.OK);
    }
    
 //-------------------Retrieve Single Inventory--------------------------------------------------------
    
    @RequestMapping(value = "/sale/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> get(@PathVariable("id") long id) {
        System.out.println("Fetching Sale with id " + id);
        Sale inventory = saleService.findById(id);
        if (inventory == null) {
            System.out.println("Sale with id " + id + " not found");
            return new ResponseEntity<Sale>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Sale>(inventory, HttpStatus.OK);
    }
    
  //-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/sale/", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Sale sale,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Sale " + sale);
       
        saleService.save(sale);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/sale/{id}").buildAndExpand(sale.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    //------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/sale/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sale> update(@PathVariable("id") long id, @RequestBody Sale sale) {
        System.out.println("Updating Sale " + id);
         
        Sale currentInventory = saleService.findById(id);
         
        if (currentInventory==null) {
            System.out.println("Sale with id " + id + " not found");
            return new ResponseEntity<Sale>(HttpStatus.NOT_FOUND);
        }
        System.out.println("SaleStatusId: " + sale.getSaleStatusId());

        currentInventory.setSaleStatusId(sale.getSaleStatusId());
         
        saleService.update(currentInventory);
        return new ResponseEntity<Sale>(currentInventory, HttpStatus.OK);
    }
   
}
