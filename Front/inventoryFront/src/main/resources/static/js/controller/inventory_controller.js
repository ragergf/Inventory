'use strict';

App.controller('InventoryController', ['$scope', '$window', 'InventoryService', function($scope, $window, InventoryService) {
          var self = this;
          self.inventory={id:null,quantity:null,price:null,lastDate:null,lastUser:null,companyId:'1',product:{id:null,name:'',description:'',barcode:'',department:{id:'7',name:'farmaceutico'}}};          
          self.inventories=[];
          self.barcode='';                                          
          
          self.fetchAllInventories = function(){
        	  console.log("controller fetchAllInventories");
              InventoryService.fetchAllInventories()
                  .then(
      					       function(d) {
      					    	   	console.log("controller level: "+d);      					    	   	
      						       	self.inventories = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createInventory = function(inventory){
        	  self.responseCreateInventory = InventoryService.createInventory(inventory)
		              .then(
                      self.fetchAllInventories, 
				              function(errResponse){
					               console.error('Error while creating Inventory.');
				              }	
                  );
              console.log(self.responseCreateInventory);
          };

         self.updateInventory = function(inventory, id){
        	 InventoryService.updateInventory(inventory, id)
		              .then(
				              self.fetchAllInventories, 
				              function(errResponse){
					               console.error('Error while updating Inventory.');
				              }	
                  );
          };

         self.deleteInventory = function(id){
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
              self.inventory={id:null,quantity:null,price:null,lastDate:null,lastUser:null,companyId:'1',product:{id:null,name:'',description:'',barcode:'',department:{id:'7',name:'farmaceutico'}}};
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
      }]);
