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
      .when '/about',
        templateUrl: 'views/about.html'
        controller: 'AboutCtrl'
      .otherwise
        redirectTo: '/'

