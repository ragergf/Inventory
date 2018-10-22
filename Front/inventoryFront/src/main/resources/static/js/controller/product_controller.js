'use strict';

App.controller('ProductController', ['$scope', 'ProductService', function($scope, ProductService) {
          var self = this;
          self.product={id:null,name:'',description:'',barcode:'', price:''};
          self.products=[];
          self.barcode='';
          self.responseCreateProduct='';
              
          self.fetchAllProducts = function(){
              ProductService.fetchAllProducts()
                  .then(
      					       function(d) {
      						        self.products = d._embedded.product;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createProduct = function(product){
        	  self.responseCreateProduct = ProductService.createProduct(product)
		              .then(
                      self.fetchAllProducts, 
				              function(errResponse){
					               console.error('Error while creating Product.');
				              }	
                  );
              console.log(self.responseCreateProduct);
          };

         self.updateProduct = function(product, id){
              ProductService.updateProduct(product, id)
		              .then(
				              self.fetchAllProducts, 
				              function(errResponse){
					               console.error('Error while updating Product.');
				              }	
                  );
          };

         self.deleteProduct = function(id){
              ProductService.deleteProduct(id)
		              .then(
				              self.fetchAllProducts, 
				              function(errResponse){
					               console.error('Error while deleting Product.');
				              }	
                  );
          };

          self.fetchAllProducts();

          self.submit = function() {
              if(self.product.id==null){
                  console.log('Saving New Product', self.product);    
                  self.createProduct(self.product);
              }else{
                  self.updateProduct(self.product, self.product.id);
                  console.log('Product updated with id ', self.product.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.products.length; i++){
                  if(self.products[i].id == id) {
                     self.product = angular.copy(self.products[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.product.id === id) {//clean form if the product to be deleted is shown there.
                 self.reset();
              }
              self.deleteProduct(id);
          };

          
          self.reset = function(){
              self.product={id:null,name:'',description:'',barcode:'', price:''};
              $scope.myForm.$setPristine(); //reset Form
          };
          
          self.searchByBarcode = function(barcode){
        	  ProductService.searchByProduct(barcode)
              .then(
            		  function(d) {
            			  	console.log(d);
					        self.product = d;
					        self.barcode='';
					        $scope.myForm.$setPristine(); //reset Form
				       }
            		  , 
		              function(errResponse){
			               console.error('Error while updating Product.');
		              }	
          );
          };

      }]);
