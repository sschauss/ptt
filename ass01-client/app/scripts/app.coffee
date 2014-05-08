'use strict'

angular
  .module('ass01ClientApp', [
    'ngResource',
    'ngSanitize',
    'ngRoute'
  ])
  .config ($routeProvider) ->
    $routeProvider
      .when '/',
        templateUrl: 'views/main.html'
        controller: 'MainCtrl'
      .when '/tracks',
        templateUrl: 'views/tracks.html'
        controller: 'TracksCtrl'
      .when '/users',
        templateUrl: 'views/users.html'
        controller: 'UsersCtrl'
      .otherwise
        redirectTo: '/'

