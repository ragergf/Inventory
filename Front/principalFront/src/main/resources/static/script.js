(function(angular) {
  'use strict';
angular.module('myApp', ['ngRoute', 'ngAnimate'])
  .config(['$routeProvider',
    function($routeProvider) {
      $routeProvider
        .when('/Inventory', {
          templateUrl: 'book.html'
        })
        .when('/Sale', {
          templateUrl: 'chapter.html'        
        });
  }]);
})(window.angular);

/*
Copyright 2018 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/