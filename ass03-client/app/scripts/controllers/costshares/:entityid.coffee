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

    $scope.onlyNumbers = /^(0|[1-9][0-9]*)((\.|,)[0-9][0-9]?)?$/;

    $scope.article =
      userEntityIds: []

    costShareEntityId = $routeParams.entityId

    Article = $resource '/api/articles/:entityId',
      entityId: '@id'

    CostShareArticle = $resource '/api/costshares/:entityId/articles',
      entityId: '@id'

    CostShare = $resource '/api/costshares/:entityId',
      entityId: '@id'

    CostShareUser = $resource '/api/costshares/:entityId/users',
      entityId: '@id'

    $scope.costShare = CostShare.get
      entityId: costShareEntityId

    $scope.costShareUsers = CostShareUser.query
      entityId: costShareEntityId

    $scope.articles = CostShareArticle.query
      entityId: costShareEntityId

    $scope.isUser = (article, entityId) ->
      for userEntityId in article.userEntityIds
        return true if userEntityId == entityId
      false

    $scope.getPurchaser = (entityId) ->
      for user in $scope.costShareUsers
        if user.entityId == entityId
          return "#{user.firstName} #{user.lastName}"

    $scope.getDateFromMilliseconds = (milliseconds) ->
      date = new Date milliseconds
      return "#{("0" + date.getDate()).slice(-2)}/#{("0" + date.getMonth()).slice(-2)}/#{date.getFullYear()}"

    $scope.addArticle = ->
      purchaseDateArray = $scope.article.purchaseDate.split "-"
      purchaseDate = new Date purchaseDateArray[0], purchaseDateArray[1], purchaseDateArray[2]
      Article.save
        purchaserEntityId: $scope.article.purchaserEntityId
        costShareEntityId: costShareEntityId
        purchaseDate: purchaseDate
        name: $scope.article.name
        value: $scope.article.value
        userEntityIds: $scope.article.userEntityIds
      , ->
        $scope.article =
          userEntityIds: []
        $scope.articles = CostShareArticle.query
          entityId: costShareEntityId

    $scope.addUser = (entityId) ->
      index = $scope.article.userEntityIds.indexOf entityId
      if index < 0
        $scope.article.userEntityIds.push entityId
      else
        $scope.article.userEntityIds.splice index, 1

    $scope.getDebts = (entityId) ->
      debts =
        overall: 0
      for article in $scope.articles
        if (entityId != article.purchaserEntityId) && (entityId in article.userEntityIds)
          debt = article.value / article.userEntityIds.length
          debts.overall += debt
          if debts[article.purchaserEntityId]
            debts[article.purchaserEntityId] += debt
          else
            debts[article.purchaserEntityId] = debt
      console.log debts
      return debts