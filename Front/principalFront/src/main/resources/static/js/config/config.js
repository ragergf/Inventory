'use strict';

App.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider, ) {		
	
	console.log("dentro de config");  
	$routeProvider
        .when('/Inventory', {
          templateUrl: 'html/inventory/inventory.html'//,
//          controller: 'BookCtrl',
//          controllerAs: 'book'
        })
        .when('/Sale', {
          templateUrl: 'html/sale/sale.html'//,
//          controller: 'ChapterCtrl',
//          controllerAs: 'chapter'
        });
      
  }]);