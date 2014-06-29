'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'MainCtrl', ($scope, $resource, $http) ->

    Token = $resource '/api/token'

    User = $resource '/api/users/:id', {id: '@id'}

    $scope.login = ->
      Token.save
        emailAddress: $scope.loginEmailAddress
        password: $scope.loginPassword
      , (credentials, headers) ->
          $http.defaults.headers.common['x-auth-token'] = headers 'x-auth-token'
          console.log $http.defaults.headers


    $scope.register = ->
      User.save
        emailAddress: $scope.registerEmailAddress
        password: $scope.registerPassword
        firstName: $scope.firstName
        lastName: $scope.lastName



