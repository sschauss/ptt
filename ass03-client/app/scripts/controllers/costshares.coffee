'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:CostsharesCtrl
 # @description
 # # CostsharesCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'CostsharesCtrl', ($scope, $resource) ->

    user =
      entityId: localStorage.getItem 'entityId'

    $scope.users = []

    CostShare = $resource '/api/costshares'

    User = $resource '/api/users'

    UserCostShare = $resource '/api/users/:entityId/costshares',
      entityId: '@id'

    $scope.costShares = UserCostShare.query
      entityId: user.entityId

    $scope.$watch 'searchString', (searchString) ->
      if searchString
        $scope.searchResult = User.query
          q: searchString

    $scope.userIndex = (user) ->
      for i, u of $scope.users
        if u.entityId == user.entityId
          return i
      return -1

    $scope.addUser = (user) ->
      if $scope.userIndex(user) == -1
        $scope.users.push user
      $scope.searchString = ''
      $scope.searchResult = []

    $scope.removeUser = (user) ->
      if $scope.userIndex(user) != -1
       $scope.users.splice($scope.userIndex(user), 1)

    $scope.create = ->
      CostShare.save
        name: $scope.name,
        userEntityIds: for user in $scope.users
          user.entityId
      , ->
        $scope.costShares = UserCostShare.query
          entityId: user.entityId