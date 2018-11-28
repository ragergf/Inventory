'use strict';

App.controller('SalesController', function($scope, InventoryService){
	var self = this;		
	
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
				}
			}
		};
	};
	
	self.itemDetails = [];
	self.total = 0;
	self.itemDetail = self.initItemDetail();
	self.itemDetailPush = null;
	self.barcode = null;
	
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
              			  console.log('barcode: '+ self.barcode);	
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
              		  , 
  		              function(errResponse){
  			               console.error('Error while updating Inventory.');
  		              }	
                );    			
	    	}else{
	    		console.log("index: ");
	    		console.log(self.itemDetails[index]);
	    		self.itemDetails[index].quantity++;	    		
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
//    		console.log(self.itemDetails[k].total);
    	}
	};
});