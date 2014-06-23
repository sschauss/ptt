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

    $scope.flatShares = FlatShare.query()
