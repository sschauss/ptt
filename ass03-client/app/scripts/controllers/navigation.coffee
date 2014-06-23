'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:NavigationCtrl
 # @description
 # # NavigationCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'NavigationCtrl', ($scope, $location) ->

    $scope.$watch ->
      $location.path()
    , ->
      $scope.active = $location.path()