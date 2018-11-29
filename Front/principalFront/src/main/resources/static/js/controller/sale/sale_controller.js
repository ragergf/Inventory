'use strict';

App.controller('SalesController', function($scope, InventoryService, SaleService, ModalService){
	var self = this;
	self.k=0;
	self.lastId=0;
	
	self.totalDay=0;
	
	self.initItemDetail = function(){
		return {	
			quantity:1,
			price:0,		
			total:0,			
			inventory:
			{
				id:null,
				product:
				{					
					name:'',
					description:'',
					barcode:''		
				},
				quantity:0
			}
		};
	};
	
	self.initSale = function(){
		return {
			id:null,
			subtotal:0,
			tax:0,
			total:0,
			saleDate:null,
			saleDetail:null		
		}
	};
	
	self.initSaleDetail = function(){
		return {
			sec:null,
			quantity:0,
			price:0,
			total:0,
			inventoryId:0	
		}
	};
	
	self.itemDetails = [];
	self.total = 0;
	self.itemDetail = self.initItemDetail();
	self.itemDetailPush = null;
	self.barcode = null;
	
	self.sale = null;
	self.itemSaleDetail=null;
	self.pushSaleDEtail=null;
	
	self.filteredArray = [];
	self.currentPage = 0;
	self.numPerPage = 5;
	self.maxSize = 5;		
	self.pager={page:0, size:self.numPerPage, arrayFilter:null, arrayLength:null};
	self.pagerDailySummary={page:0, size:1, arrayFilter:null, arrayLength:null};
	
	self.today = function()
	{
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!

		var yyyy = today.getFullYear();
		if (dd < 10) {
		  dd = '0' + dd;
		} 
		if (mm < 10) {
		  mm = '0' + mm;
		} 
		return dd + '-' + mm + '-' + yyyy;		
	};
	
	self.fetchAllSalesDateRange = function(){
		
		console.log("controller fetchAllInventories");
  	  	SaleService.open(self, "Loading...");
  	  
  	  	console.log(self.pager);
        SaleService.fetchAllSalesDateRangePager(self.pager, self.today(), self.today())
    	.then(
        		function(d) {
        			console.log("pager level: "+d);      					    	   	
        			self.inventories = d.arrayFilter;      							  							        							
        			self.ArrayLength = d.arrayLength;
        			self.filteredArray =d.arrayFilter;   
        			
        		},
  				function(errResponse){
  					console.error('Error while fetching Currencies');
  				}
    	);
        SaleService.fetchDailySummary(self.pagerDailySummary, self.today(), self.today()).then(
	        	function(d)
	        	{
	        		console.log(d);
	        		if(d == "")
	        			self.totalDay = 0;
	        		else
	        			self.totalDay = d.arrayFilter[0].total;
	        	}
	        	,
	        	function(errResponse){
					console.error('Error while fetching Currencies');
				}
	        );
	};
	
	self.removeItemDetail = function(index){
		self.itemDetails.splice(index,1);
		self.sum();
	}
	
	self.addItemDetail = function($event){
		console.log("tecleo un dato");
	    var keyCode = $event.which || $event.keyCode;
	    var index=0;
	  
	    if (keyCode === 13) {
	    	console.log(self.itemDetail);
	    	if (!self.itemDetail.inventory.product.barcode) {return;}  
	    	index=-1;
	    	for(var k = 0; k < self.itemDetails.length; k++){
	    		if(self.itemDetails[k].inventory.product.barcode == self.itemDetail.inventory.product.barcode) {
                    index=k;
                    break;
	    		}
	    	}
	    	
	    	console.log("index: "+index);
	    	self.barcode = self.itemDetail.inventory.product.barcode;
	    	
	    	if (index == -1){	    		
    			InventoryService.searchByBarcode(self.barcode)
                .then(
              		  function(d) {
              			if(d.quantity >= 1)
              			{
              			  console.log('barcode: '+ self.barcode);
              			  self.itemDetail.inventory.id = d.id;
              			  self.itemDetail.inventory.quantity = d.quantity;
              			  self.itemDetail.inventory.product.name = d.product.name;
              			  self.itemDetail.inventory.product.description = d.product.description;
              			  self.itemDetail.price = d.price;
              			  self.itemDetail.inventory.product.barcode = self.barcode;
              			  self.itemDetailPush = angular.copy(self.itemDetail);
              			  self.itemDetails.push(self.itemDetailPush);
              			  console.log('push');
              			  console.log(self.itemDetailPush);
              			  self.itemDetail = self.initItemDetail();
              			  self.sum();
//              			console.log(self.itemDetail);
              			}
              			else              				
              			{
              				ModalService.changeShowButtonOk(true);
              				SaleService.open(self, "Sold Out!!!");
              			}
  				       }
              		  , 
  		              function(errResponse){
  			               console.error('Error while updating Inventory.');
  			               console.error(errResponse);
  			               if(errResponse.status == 404)
  			               {
  			            	   	ModalService.changeShowButtonOk(true);
  			            	   	SaleService.open(self, "Unregistered Product!!!");
  			               }
  			               else
  			               {
  			            	   	ModalService.changeShowButtonOk(true);
               					SaleService.open(self, "Problems with the search!!!");
  			               }
  		              }	
                );    			
	    	}else{
	    		console.log("index: ");
	    		console.log(self.itemDetails[index]);
	    		if(self.itemDetails[index].inventory.quantity > self.itemDetails[index].quantity)
	    			self.itemDetails[index].quantity++;	    	
	    		else
	    		{
	    			ModalService.changeShowButtonOk(true);
      				SaleService.open(self, "Sold Out!!!");
	    		}
	    		self.itemDetail = self.initItemDetail();
	    		self.sum();
	    	}		    		    	
	    	
	    }

	};
	
	self.sum = function()
	{
		self.total=0;
		for(var k = 0; k < self.itemDetails.length; k++){			
			self.total += (self.itemDetails[k].quantity*self.itemDetails[k].price);
    	}
	};
	
	self.validateQuantity = function($event, quantity, index)
	{
		console.log($event);
		if($event.currentTarget.value > quantity)
		{
			ModalService.changeShowButtonOk(true);
			SaleService.open(self, "Sold Out!!!");
			self.itemDetails[index].quantity = quantity;
			self.sum();
		}
	};
	
	self.executeSale = function()
	{
		self.sale = self.initSale();
		self.sale.total = self.total;
		self.sale.saleDetail = [];
		for(var k = 0; k < self.itemDetails.length; k++){			
//			self.total += (self.itemDetails[k].quantity*self.itemDetails[k].price);
			self.itemSaleDetail = self.initSaleDetail();
			self.itemSaleDetail.quantity = self.itemDetails[k].quantity;
			self.itemSaleDetail.price = self.itemDetails[k].price;
			self.itemSaleDetail.total = self.itemDetails[k].price * self.itemDetails[k].quantity;
			self.itemSaleDetail.inventoryId = self.itemDetails[k].inventory.id;
			self.pushSaleDetail = angular.copy(self.itemSaleDetail);
			self.sale.saleDetail.push(self.pushSaleDetail);			
    	}
		console.log("Creando Venta");
		console.log(self.sale);
		self.createSale(self.sale);

	};	
	
	self.createSale = function(sale)
	{
		SaleService.open(self, "Saving...");
		SaleService.createSale(sale)
        .then(
        	function(){
        		self.clean();
        		self.fetchAllSalesDateRange();
        	}
        	, 
        	function(errResponse){
        		console.error('Error while creating Inventory.' + errResponse);
        	}	
        );
	
	};
	
	self.updateSale = function(sale, id){
   	 SaleService.open(self, "Updating...");
   	 SaleService.updateSale(sale, id)
	              .then(
	            		  self.fetchAllSalesDateRange, 
			              function(errResponse){
				               console.error('Error while updating sale.');
			              }	
             );
    };
     
    self.changeStatus = function(id, status)
    {
    	console.log("On ChangeStatus...");
    	for(var k = 0; k < self.filteredArray.length; k++){
    		if(id == self.filteredArray[k].id)
    		{
    			self.sale = angular.copy(self.filteredArray[k]);
    			self.sale.saleStatusId = status;
    			self.updateSale(self.sale, id);
    		}
    	}
    };
	
	self.clean = function ()
	{
		self.itemDetails = [];
		self.total = 0;

	};
	
	self.disabledButtons = function ()
	{
		if(self.itemDetails.length == 0 || self.total == 0 || isNaN(self.total))
			return true;
		else
			return false;
	};
	
	self.onPageChange = function() {
  	  self.pager.page=self.currentPage-1;
  	  SaleService.fetchAllSalesPager(self.pager)
  	  	.then(
	    			function(d) {
	    				console.log("pager level: "+d);      					    	   	
	    				self.inventories = d.arrayFilter;      							  							        							
	    				self.ArrayLength = d.arrayLength;
				    	self.filteredArray =d.arrayFilter;
	    			},
					function(errResponse){
						console.error('Error while fetching Currencies');
					}
	    	);
    };                      
    
    self.fetchAllSalesDateRange();
});