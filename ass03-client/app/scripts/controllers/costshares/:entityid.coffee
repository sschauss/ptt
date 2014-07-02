'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:CostsharesEntityidCtrl
 # @description
 # # CostsharesEntityidCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'CostsharesEntityidCtrl', ($scope, $resource, $routeParams) ->

    CostShare = $resource '/api/costshares/:entityId'

    CostShareUser = $resource '/api/costshares/:entityId/users',
      entityId: '@id'

    $scope.costShare = CostShare.get
      entityId: $routeParams.entityId

    $scope.costShareUsers = CostShareUser.query
      entityId: $routeParams.entityId