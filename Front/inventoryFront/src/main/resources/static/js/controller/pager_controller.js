'use strict';

App.controller('controller', function($rootScope,$scope, $filter) {

	  //Variable initialization
	  $scope.filteredArray = [];
	  $scope.currentPage = 0;
	  $scope.numPerPage = 5;
	  $scope.maxSize = 8;
	  $scope.arrayFilter = '';	 	  	 

	  $scope.sortType = ''; // set the default sort type
	  $scope.sortReverse = true; // set the default sort order

//	  //Creates the items
//	  $scope.arrayList = $scope.inventories;
//	  
//	 
//	  //$scope.filteredArray = $scope.arrayList.slice(0, 5);
//	  $scope.ArrayLength = $scope.arrayList.length;
//	  $scope.filteredArray = $scope.arrayList.slice(0, $scope.numPerPage);

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

	});