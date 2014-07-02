'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'MainCtrl', ($scope, $resource, $location) ->

    Login = $resource '/api/users/login'

    User = $resource '/api/users/:id', {id: '@id'}

    $scope.login = ->
      Login.save
        emailAddress: $scope.loginEmailAddress
        password: $scope.loginPassword
      , (credentials, headers) ->
        localStorage.setItem 'entityId', headers 'x-entityId'
        $location.path '/costshares'



    $scope.register = ->
      User.save
        emailAddress: $scope.registerEmailAddress
        password: $scope.registerPassword
        firstName: $scope.firstName
        lastName: $scope.lastName