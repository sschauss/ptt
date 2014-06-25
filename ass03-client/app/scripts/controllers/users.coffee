'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:UsersCtrl
 # @description
 # # UsersCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'UsersCtrl', ($scope) ->
    $scope.awesomeThings = [
      'HTML5 Boilerplate'
      'AngularJS'
      'Karma'
    ]
