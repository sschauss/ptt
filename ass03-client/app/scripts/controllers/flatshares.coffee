'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:FlatsharesCtrl
 # @description
 # # FlatsharesCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'FlatsharesCtrl', ($scope, $resource) ->
    FlatShare = $resource '/api/flatshares/:id', {id: '@id'}
    User = $resource '/api/flatshares/:id/users', {id: '@id'}

    $scope.flatShares = FlatShare.query()

    $scope.users = {}


    $scope.showUsers = (id) ->
      $scope.users[id] = User.query {id: id}

    $scope.hideUsers = (id) ->
      delete $scope.users[id]
