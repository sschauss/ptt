'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'MainCtrl', ($scope) ->
    $scope.awesomeThings = [
      'HTML5 Boilerplate'
      'AngularJS'
      'Karma'
    ]
