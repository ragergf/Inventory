'use strict';

App.factory('ProductService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllProducts: function() {
					return $http.get('http://192.168.0.22:8080/product/')
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
					return $http.post('http://192.168.0.22:8080/product/', product)
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
					return $http.put('http://192.168.0.22:8080/product/'+id, product)
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
					return $http.delete('http://192.168.0.22:8080/product/'+id)
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
					return $http.get('http://192.168.0.22:8080/product/search/findByBarcode?barcode='+barcode)
					.then(
									function(response){										
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting product');
										return $q.reject(errResponse);
									}
							);
					
			}
		
	};

}]);
