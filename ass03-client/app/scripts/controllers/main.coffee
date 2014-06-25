'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'MainCtrl', ($scope, $resource, $controller) ->

    Token = $resource "/api/login"

    User = $resource "/api/users/:id", {id: '@id'}

    $scope.login = ->
      Token.save
        emailAddress: $scope.loginEmailAddress
        password: $scope.loginPassword
      , (credentials, headers) ->
          localStorage.setItem "x-auth-token", headers("x-auth-token")


    $scope.register = ->
      User.save
        emailAddress: $scope.registerEmailAddress
        password: $scope.registerPassword
        firstName: $scope.firstName
        lastName: $scope.lastName



