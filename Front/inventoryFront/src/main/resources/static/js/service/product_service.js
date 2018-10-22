'use strict';

App.factory('ProductService', ['$http', '$q', function($http, $q){

	var server = '192.168.0.9';
	var port = '8080';

	return {
		
			fetchAllProducts: function() {
					return $http.get('http://'+server+':'+port+'/product/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching product');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createProduct: function(product){
					return $http.post('http://'+server+':'+port+'/product/', product)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating product');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateProduct: function(product, id){
					return $http.put('http://'+server+':'+port+'/product/'+id, product)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating product');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteProduct: function(id){
					return $http.delete('http://'+server+':'+port+'/product/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting product');
										return $q.reject(errResponse);
									}
							);
			},
			
			searchByProduct: function(barcode){
					return $http.get('http://'+server+':'+port+'/product/search/findByBarcode?barcode='+barcode)
					.then(
									function(response){										
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting product');
										return $q.reject(errResponse);
									}
							);
					
			},
			
			createInventory: function(productResponse){
					console.log("productResponse: "+productResponse);
			}
		
	};

}]);
