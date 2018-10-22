'use strict';

App.factory('InventoryService', ['$http', '$q', '$window', function($http, $q, $window){

	var server = '192.168.0.9';
	var port = '8080';
	var module = 'inventory';

	return {
		
			fetchAllInventories: function() {
					return $http.get('http://'+server+':'+port+'/'+module+'/')
							.then(
									function(response){
										console.log(response);
										if(response.status == 204)
											$window.alert("No se han registrado productos");
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching product');
										$window.alert("Ha ocurrido un error!!");
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createInventory: function(inventory){
					return $http.post('http://'+server+':'+port+'/'+module+'/', inventory)
							.then(
									function(response){
										$window.alert("Se ha agregado un nuevo producto con exito!!");
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating product');
										if(errResponse.status == 409)
											$window.alert("Ya existe un producto con ese nombre");
										else if(errResponse.status == 423)
											$window.alert("Ya existe un producto con ese codigo de barras");
										else
											$window.alert("Ha ocurrido un error al buscar el producto!!");
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateInventory: function(inventory, id){
					return $http.put('http://'+server+':'+port+'/'+module+'/'+id, inventory)
							.then(
									function(response){
										$window.alert("Se ha actualizado el producto con exito!!");
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating product');
										if (errResponse.status == 409)
											$window.alert("No existe ese producto");
										else if(errResponse.status == 409)
											$window.alert("Ya existe un producto con ese nombre");
										else if(errResponse.status == 423)
											$window.alert("Ya existe un producto con ese codigo de barras");
										else
											$window.alert("Ha ocurrido un error al buscar el producto!!");
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteInventory: function(id){
					return $http.delete('http://'+server+':'+port+'/'+module+'/'+id)
							.then(
									function(response){
										$window.alert("Se ha borrado el producto con exito!!");
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting product');
										$window.alert("Ha ocurrido un error al borrar el producto!!");
										return $q.reject(errResponse);
									}
							);
			},
			
			searchByBarcode: function(barcode){
					return $http.get('http://'+server+':'+port+'/'+module+'/search/findByBarcode?barcode='+barcode)
					.then(
									function(response){															
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while search product');
										if(errResponse.status == 404)
											$window.alert("No existe un producto con ese codigo de barras!!");
										else
											$window.alert("Ha ocurrido un error al buscar el producto!!");
										return $q.reject(errResponse);
									}
							);
					
			}							
	};

}]);
