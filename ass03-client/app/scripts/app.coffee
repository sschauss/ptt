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
      .when '/costshares',
        templateUrl: 'views/costshares.html'
        controller: 'CostsharesCtrl'
      .when '/costshares/:entityId',
        templateUrl: 'views/costshares/:entityid.html'
        controller: 'CostsharesEntityidCtrl'
      .otherwise
        redirectTo: '/'