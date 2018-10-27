'use strict';

App.factory('InventoryService', ['$http', '$q', "$uibModal", "$log", function($http, $q, $uibModal, $log){

	var server = '192.168.0.3';
	var port = '8080';
	var module = 'inventory';
	var modalInstance;
	
	return {
		
		open: function (self, mensaje) {
		    modalInstance = $uibModal.open({
		      animation: true,
		      ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',
		      templateUrl: 'html/modal.html',
		      controller: 'ModalInstanceCtrl',		      
		      controllerAs: 'pc',
		      backdrop: 'static',
		      resolve: {
		        data: function () {
		          return mensaje;
		        }
		      }
		    });
	 	},
		
			fetchAllInventories: function() {
					
					return $http.get('http://'+server+':'+port+'/'+module+'/')
							.then(
									function(response){
										console.log(response);
										modalInstance.close();
										if(response.status == 204)
											console.log("No se han registrado productos");										
										return response.data;
									}, 
									function(errResponse){
										modalInstance.close();
										console.error('Error while fetching product');
										console.log("Ha ocurrido un error!!");
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createInventory: function(inventory){
					return $http.post('http://'+server+':'+port+'/'+module+'/', inventory)
							.then(
									function(response){
										modalInstance.close();
										console.log("Se ha agregado un nuevo producto con exito!!");
										return response.data;
									}, 
									function(errResponse){
										modalInstance.close();
										console.error('Error while creating product');
										if(errResponse.status == 409)
											alett.log("Ya existe un producto con ese nombre");
										else if(errResponse.status == 423)
											console.log("Ya existe un producto con ese codigo de barras");
										else
											console.log("Ha ocurrido un error al buscar el producto!!");
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateInventory: function(inventory, id){
					return $http.put('http://'+server+':'+port+'/'+module+'/'+id, inventory)
							.then(
									function(response){
										modalInstance.close();
										console.log("Se ha actualizado el producto con exito!!");
										return response.data;
									}, 
									function(errResponse){
										modalInstance.close();
										console.error('Error while updating product');
										if (errResponse.status == 409)
											console.log("No existe ese producto");
										else if(errResponse.status == 409)
											console.log("Ya existe un producto con ese nombre");
										else if(errResponse.status == 423)
											console.log("Ya existe un producto con ese codigo de barras");
										else
											console.log("Ha ocurrido un error al buscar el producto!!");
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteInventory: function(id){
					return $http.delete('http://'+server+':'+port+'/'+module+'/'+id)
							.then(
									function(response){
										modalInstance.close();
										console.log("Se ha borrado el producto con exito!!");
										return response.data;
									}, 
									function(errResponse){
										modalInstance.close();
										console.error('Error while deleting product');
										console.log("Ha ocurrido un error al borrar el producto!!");
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
											console.log("No existe un producto con ese codigo de barras!!");
										else
											console.log("Ha ocurrido un error al buscar el producto!!");
										return $q.reject(errResponse);
									}
							);
					
			}							
	};

}]);
