'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:CostshareCtrl
 # @description
 # # CostshareCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'CostshareCtrl', ($scope, $resource, $location) ->
    share1 =
      name: "Lützel-WG"
      userCount: 3
      id: "wg1"

    share2 =
      name: "Rübenach-WG"
      userCount: 3
      id: "wg2"

    share3 =
      name: "Bienenstück-WG"
      userCount: 5
      id: "wg3"

    $scope.costShares = [
      share1,
      share2,
      share3
    ]

    $scope.changeRoute = (newRoute) ->
      $location.path("costshare/:"+newRoute);

    CostShares = $resource '/api/costshares'

  #TODO use server json
  #$scope.costShares = CostShares.query()
