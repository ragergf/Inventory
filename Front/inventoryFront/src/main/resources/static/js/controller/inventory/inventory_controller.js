'use strict';

App.controller('InventoryController', ['$scope', 'InventoryService', function($scope, InventoryService) {          			  	 		
			var self = this;
			self.inventory={id:null,quantity:null,price:null,lastDate:null,lastUser:null,companyId:'1',product:{id:null,name:'',description:'',barcode:'',department:{id:'1',name:'farmaceutico'}}};
	          
			self.inventories=[];
			self.barcode='';   
			self.prueba='';
	          
			self.filteredArray = [];
	  		self.currentPage = 0;
	  		self.numPerPage = 4;
  			self.maxSize = 8;		
  			self.pager={page:0, size:self.numPerPage, arrayFilter:null, arrayLength:null};
          
  			
          
       
  			self.fetchAllInventories = function(){
        	  console.log("controller fetchAllInventories");
        	  InventoryService.open(self, "Loading...");
        	  
        	  console.log(self.pager);
              InventoryService.fetchAllInventoriesPager(self.pager)
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
           
          self.createInventory = function(inventory){
        	  InventoryService.open(self, "Saving...");
        	  InventoryService.createInventory(inventory)
		              .then(
                      self.fetchAllInventories, 
				              function(errResponse){
					               console.error('Error while creating Inventory.');
				              }	
                  );
              console.log(self.responseCreateInventory);
          };

         self.updateInventory = function(inventory, id){
        	 InventoryService.open(self, "Updating...");
        	 InventoryService.updateInventory(inventory, id)
		              .then(
				              self.fetchAllInventories, 
				              function(errResponse){
					               console.error('Error while updating Inventory.');
				              }	
                  );
          };

         self.deleteInventory = function(id){
        	 InventoryService.open(self, "Delating...");
             InventoryService.deleteInventory(id)
		              .then(
				              self.fetchAllInventories, 
				              function(errResponse){
					               console.error('Error while deleting Inventory.');
				              }	
                  );
          };
          console.log("Antes del primer request");
          self.fetchAllInventories();
          console.log("Despues del primer request");
          self.submit = function() {
              if(self.inventory.id==null){
                  console.log('Saving New Inventory', self.inventory);    
                  self.createInventory(self.inventory);
                  }else{
                  self.updateInventory(self.inventory, self.inventory.id);
                  console.log('Inventory updated with id ', self.inventory.id);
              }
              self.reset();
          };
          console.log("Despues de submit");
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.inventories.length; i++){
                  if(self.inventories[i].id == id) {
                     self.inventory = angular.copy(self.inventories[i]);
                     break;
                  }
              }
          };
          console.log("Despues de edit");
          
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.inventory.id === id) {//clean form if the inventory to be deleted is shown there.
                 self.reset();
              }
              self.deleteInventory(id);
          };
          console.log("Despues de remove");
          
          self.reset = function(){
              self.inventory={id:null,quantity:null,price:null,lastDate:null,lastUser:null,companyId:'1',product:{id:null,name:'',description:'',barcode:'',department:{id:'1',name:'farmaceutico'}}};
              $scope.myForm.$setPristine(); //reset Form
          };
          console.log("Despues de reset");
          
          self.searchByBarcode = function(barcode){
        	  InventoryService.searchByBarcode(barcode)
              .then(
            		  function(d) {
            			  	console.log(d);
					        self.inventory = d;
					        self.barcode='';
					        $scope.myForm.$setPristine(); //reset Form
				       }
            		  , 
		              function(errResponse){
			               console.error('Error while updating Inventory.');
		              }	
              );
          };
          console.log("Despues de barcode");
          
          self.onPageChange = function() {
        	  self.pager.page=self.currentPage-1;
        	  InventoryService.fetchAllInventoriesPager(self.pager)
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
          }      	 
      }]);
