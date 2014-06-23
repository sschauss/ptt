'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:AboutCtrl
 # @description
 # # AboutCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'AboutCtrl', ($scope) ->
    $scope.awesomeThings = [
      'HTML5 Boilerplate'
      'AngularJS'
      'Karma'
    ]
