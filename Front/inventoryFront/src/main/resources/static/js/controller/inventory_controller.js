'use strict';

App.controller('InventoryController', ['$scope', 'InventoryService', function($scope, InventoryService) {
          
		$scope.filteredArray = [];
		$scope.currentPage = 0;
		$scope.numPerPage = 10;
		$scope.maxSize = 8;
		$scope.arrayFilter = '';	 	  	 

		$scope.sortType = ''; // set the default sort type
		$scope.sortReverse = true; // set the default sort order
		
		var self = this;
          self.inventory={id:null,quantity:null,price:null,lastDate:null,lastUser:null,companyId:'1',product:{id:null,name:'',description:'',barcode:'',department:{id:'1',name:'farmaceutico'}}};          
          self.inventories=[];
          self.barcode='';   
          self.prueba='';
          
          $scope.mensaje= "Hola pager";
          
          console.log('mensaje:' + $scope.mensaje);
          console.log('currentPage:' + $scope.currentPage);
          
          self.fetchAllInventories = function(){
        	  console.log("controller fetchAllInventories");
        	  InventoryService.open(self, "Loading...");
        	  console.log("Despues de open");
              InventoryService.fetchAllInventories()
                  .then(
      					       function(d) {
      					    	   	console.log("controller level: "+d);      					    	   	
      						       	self.inventories = d;      						       	
      							  //Creates the items
      						    //Creates the items
      							  $scope.arrayList = d;      							        							
      							  $scope.ArrayLength = $scope.arrayList.length;
      							  $scope.filteredArray = $scope.arrayList.slice(0, $scope.numPerPage);
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
          
          $scope.onPageChange = function() {
      	    //$scope.filterFunction();
      	    $scope.begin = (($scope.currentPage - 1) * $scope.numPerPage);
      	    $scope.end = $scope.begin + $scope.numPerPage;
      	    $scope.filteredArray = $scope.arrayList.slice($scope.begin, $scope.end);
      	  }

      	  $scope.sort = function(field) {
      	    $scope.sortType = field;
      	    $scope.sortReverse = !$scope.sortReverse;
      	    var sorted = $filter('orderBy')($scope.arrayList, field, $scope.sortReverse);
      	    $scope.arrayList = sorted;
      	    $scope.filteredArray = sorted.slice(0, $scope.arrayList.length);
      	  }

      	  $scope.filterFunction = function() {

      	    var filtered = $filter('filter')($scope.arrayList, $scope.arrayFilter);

      	    // $scope.arrayList = filtered;
      	    $scope.ArrayLength = filtered.length;

      	    //perform the paging
      	    $scope.filteredArray = filtered.slice(0, 5);
      	  };
      }]);
