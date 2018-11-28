'use strict';

App.factory('SaleService', ['$http', '$q', "$uibModal", "$log", "InventoryService", function($http, $q, $uibModal, $log, InventoryService){

	var server = '192.168.1.150';
	var port = '8081';
	var module = 'sale';
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
	 	close: function(){
	 		modalInstance.close();
	 	},
	 	
	 	fetchDailySummary: function(pager, startDate, endDate)
	 	{
	 		return $http.get('http://'+server+':'+port+'/daily/summary/range/pager?page='+pager.page+'&size='+pager.size
					+'&startDate='+startDate+'&endDate='+endDate)
			.then(
					function(response){
						console.log(response);
						modalInstance.close();
						if(response.status == 204)
							console.log("No se han registrado ventas");										
						return response.data;
					}, 
					function(errResponse){
						modalInstance.close();
						console.error('Error while fetching sale');
						console.log("Ha ocurrido un error!!");
						return $q.reject(errResponse);
					}
			);
	 	},
			fetchAllSales: function() {
					
					return $http.get('http://'+server+':'+port+'/'+module+'')
							.then(
									function(response){
										console.log(response);
										modalInstance.close();
										if(response.status == 204)
											console.log("No se han registrado ventas");										
										return response.data;
									}, 
									function(errResponse){
										modalInstance.close();
										console.error('Error while fetching sale');
										console.log("Ha ocurrido un error!!");
										return $q.reject(errResponse);
									}
							);
			},
			
			fetchAllSalesPager: function(pager) {
				
				return $http.get('http://'+server+':'+port+'/'+module+'/pager?page='+pager.page+'&size='+pager.size)
						.then(
								function(response){
									console.log(response);
									modalInstance.close();
									if(response.status == 204)
										console.log("No se han registrado ventas");										
									return response.data;
								}, 
								function(errResponse){
									modalInstance.close();
									console.error('Error while fetching sale');
									console.log("Ha ocurrido un error!!");
									return $q.reject(errResponse);
								}
						);
			},
			
			fetchAllSalesDateRangePager: function(pager, startDate, endDate) {
				
				return $http.get('http://'+server+':'+port+'/'+module+'/range/pager?page='+pager.page+'&size='+pager.size
						+'&startDate='+startDate+'&endDate='+endDate)
						.then(
								function(response){
									console.log(response);
									modalInstance.close();
									if(response.status == 204)
										console.log("No se han registrado ventas");										
									return response.data;
								}, 
								function(errResponse){
									modalInstance.close();
									console.error('Error while fetching sale');
									console.log("Ha ocurrido un error!!");
									return $q.reject(errResponse);
								}
						);
			},
		    
		    createSale: function(sale){
					return $http.post('http://'+server+':'+port+'/'+module+'/', sale)
							.then(
									function(response){
										modalInstance.close();
										InventoryService.open(self, "Updating Inventory...");
										console.log("Se ha agregado una nueva venta con exito!!");										
										for(var k = 0; k < sale.saleDetail.length; k++)
										{
											console.log('k = '+k);
											InventoryService.fetchInventoryById(sale.saleDetail[k].inventoryId, sale.saleDetail[k].quantity).then(
												function(d){
													console.log('Inventario encontrado');													
													console.log(d);//													
													InventoryService.updateInventory(d, d.id).then(
														function(d){															
															console.log('inventario actualizado')
															console.log(d);
														}
														,
														function(errResponse){
															console.error('Error while fetching Currencies');
														}
													);
												}
												,
												function(errResponse){
													console.error('Error while fetching Currencies');
												}
											);
										}
										return response.data;
									}, 
									function(errResponse){
										modalInstance.close();
										console.error('Error while creating sale');
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
		    updateSale: function(sale, id){
				return $http.put('http://'+server+':'+port+'/'+module+'/'+id, sale)
						.then(
								function(response){
									modalInstance.close();
									console.log("Se ha actualizado la venta con exito!!");
									return response.data;
								}, 
								function(errResponse){
									modalInstance.close();
									console.error('Error while updating sale');
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
		    }
	};

}]);
