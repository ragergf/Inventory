'use strict';

App.controller('MainCtrl', ['$scope','$route', '$routeParams', '$location',
    function MainCtrl($scope, $route, $routeParams, $location) {
	var self = this;
	
	  self.limpiaTabs = function (){
		  console.log("dentro de MainCtrl");
		  var elements = document.getElementsByClassName("nav-item");
		  console.log(elements);
		  var i;
		  for (i = 0; i < elements.length; i++) { 	     		  
			  angular.element(elements[i]).removeClass("active");
		  }
	  };
	  
	  self.selectTab = function ($event){
		  var busca = true;
		  self.limpiaTabs();
		  var element = angular.element($event.target);;
		  while(busca)
		  {		
			  element = element.parent();
			  console.log(element.prop("tagName"));
			  if(element.prop("tagName") == "LI")
			  {
				  busca = false;
			  }			  
		  }
		  
		  element.addClass("active");
		  
//		  angular.element($event.target).parent().addClass("active");
//		  console.log(angular.element($event.target).prop('tagName'));
//		  console.log(angular.element($event.target).parent().parent());
	  };
	  
//	  var element = angular.element($event.target);
//	  angular.element(document.querySelector(".nav-item")).removeClass("active");
//	  angular.element(document.querySelector("li")).removeClass("active");
//      this.$route = $route;
//      this.$location = $location;
//      this.$routeParams = $routeParams;
  }]);