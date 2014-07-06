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

    costShareEntityId = $routeParams.entityId

    Article = $resource '/api/articles/:entityId',
      entityId: '@id',
        update:
          method: 'POST'

    CostShareArticle = $resource '/api/costshares/:entityId/articles',
      entityId: '@id'

    CostShare = $resource '/api/costshares/:entityId',
      entityId: '@id'

    CostShareUser = $resource '/api/costshares/:entityId/users',
      entityId: '@id'

    $scope.Math = Math

    $scope.currencyPattern = /^(0|[1-9][0-9]*)((\.|,)[0-9][0-9]?)?$/;

    $scope.articleToAdd =
      purchaseDate: new Date()
      userEntityIdSelection: {}

    $scope.articleToEdit =
      userEntityIdSelection: {}

    $scope.costShare = CostShare.get
      entityId: costShareEntityId

    $scope.costShareUsers = CostShareUser.query
      entityId: costShareEntityId

    $scope.articles = CostShareArticle.query
      entityId: costShareEntityId

    $scope.calculationMethod = "Standard"

    $scope.getDateFromMilliseconds = (milliseconds) ->
      return new Date(milliseconds)

    $scope.nTimes = (n) ->
      i for i in [1..Math.ceil(n)]

    $scope.switchCalculationMethod = (method) ->
      switch method
        when "Standard" then $scope.calculationMethod = "Standard"
        when "Offsetted" then $scope.calculationMethod = "Offsetted"
        when "Fully Offsetted" then $scope.calculationMethod = "Fully Offsetted"
        else $scope.calculationMethod = "Standard"

    $scope.isUser = (article, entityId) ->
      for userEntityId in article.userEntityIds
        return true if userEntityId == entityId
      false

    $scope.getPurchaser = (entityId) ->
      for user in $scope.costShareUsers
        if user.entityId == entityId
          return "#{user.firstName} #{user.lastName}"

    $scope.addArticle = ->
      purchaserEntityId = $scope.articleToAdd.purchaserEntityId
      purchaseDate = $scope.articleToAdd.purchaseDate
      name = $scope.articleToAdd.name
      value = $scope.articleToAdd.value
      userEntityIds = []

      for userEntityId, selected of $scope.articleToAdd.userEntityIdSelection
        userEntityIds.push userEntityId if selected

      Article.save
        purchaserEntityId: purchaserEntityId
        costShareEntityId: costShareEntityId
        purchaseDate: purchaseDate
        name: name
        value: value
        userEntityIds: userEntityIds
      , ->
        $scope.articles = CostShareArticle.query
          entityId: costShareEntityId

    $scope.updateArticle = ->
      entityId = $scope.articleToEdit.entityId
      console.log entityId
      purchaserEntityId = $scope.articleToEdit.purchaserEntityId
      purchaseDate = $scope.articleToEdit.purchaseDate
      name = $scope.articleToEdit.name
      value = $scope.articleToEdit.value
      userEntityIds = []

      for userEntityId, selected of $scope.articleToEdit.userEntityIdSelection
        userEntityIds.push userEntityId if selected

      Article.update
        entityId: entityId
      ,
        entityId: entityId
        purchaserEntityId: purchaserEntityId
        purchaseDate: purchaseDate
        name: name
        value: value
        userEntityIds: userEntityIds
      , ->
        $scope.articles = CostShareArticle.query
          entityId: costShareEntityId
        , ->
          $scope.articleToEdit.entityId = null

    $scope.deleteArticle = (entityId, index) ->
      Article.delete
        entityId: entityId
      , ->
        console.log index
        $scope.articles.splice index, 1

    $scope.getDebts = (entityId, calculationType) ->
      switch calculationType
        when 'Standard' then getStandardDebts entityId
        when 'Balanced' then getBalancedDebts entityId
        else getStandardDebts entityId

    getStandardDebts = (entityId) ->
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
      return debts

    getBalancedDebts = (entityId) ->
      balancedDebts =
        overall: 0
      debts = $scope.getDebts(entityId)
      for user in $scope.costShareUsers
        userDebts = $scope.getDebts(user.entityId)
        debts[user.entityId] = 0 if !debts[user.entityId]
        userDebts[entityId] = 0 if !userDebts[entityId]
        if debts[user.entityId] > userDebts[entityId]
          balancedDebts[user.entityId] = debts[user.entityId] - userDebts[entityId]
          balancedDebts.overall += balancedDebts[user.entityId]
      return balancedDebts