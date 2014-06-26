'use strict'

###*
 # @ngdoc overview
 # @name ass03ClientApp
 # @description
 # # ass03ClientApp
 #
 # Main module of the application.
###
angular
  .module('ass03ClientApp', [
    'ngAnimate',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config ($routeProvider) ->
    $routeProvider
      .when '/',
        templateUrl: 'views/main.html'
        controller: 'MainCtrl'
      .when '/costshare',
        templateUrl: 'views/costshare.html'
        controller: 'CostshareCtrl'
      .when '/costshare/:id',
        templateUrl: 'views/costshare/:id.html'
        controller: 'CostshareIdCtrl'
      .otherwise
        redirectTo: '/'
