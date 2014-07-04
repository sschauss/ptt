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

    $scope.calculationMethod = "Standard"

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
      return debts

    $scope.getOffsettedDebts = (entityId) ->
      offsettedDebts =
        overall: 0
      debts = $scope.getDebts(entityId)
      for debtee in $scope.costShareUsers
        debteesDebts = $scope.getDebts(debtee.entityId)
        if debts[debtee.entityId] > debteesDebts[entityId]
          offsettedDebts[debtee.entityId] = debts[debtee.entityId] - debteesDebts[entityId]
          offsettedDebts.overall += offsettedDebts[debtee.entityId]
      return offsettedDebts






    paginationBarPreview = 2 #TODO rename

    $scope.setArticlesPerPage = (n) ->
      $scope.articlesPerPage = n
      $scope.pages = Math.ceil($scope.articles.length / $scope.articlesPerPage)
      $scope.turnThePage(1)

    getPagination = () ->
      l = $scope.page
      r = $scope.page
      l++ if l == 1
      r-- if r == $scope.pages
      fieldsToDisplay = paginationBarPreview * 2
      fieldsToDisplay++ if $scope.page == 1 or $scope.page == $scope.pages
      $scope.paginationLeftDots = if $scope.page - paginationBarPreview > 2 then true else false
      $scope.paginationRightDots = if $scope.page + paginationBarPreview < $scope.pages - 1 then true else false
      fieldsToDisplay++ if $scope.paginationLeftDots == false
      fieldsToDisplay++ if $scope.paginationRightDots == false
      for j in [1..paginationBarPreview]
        if l > 2 and fieldsToDisplay > 0
          l--
          fieldsToDisplay--
      for j in [1..paginationBarPreview]
        if r < $scope.pages - 1 and fieldsToDisplay > 0
          r++
          fieldsToDisplay--
      while fieldsToDisplay > 0 and l > 2
        l--
        fieldsToDisplay--
      while fieldsToDisplay > 0 and r < $scope.pages - 1
        r++
        fieldsToDisplay--
      result = []
      result.push(i) for i in [l..r]
      result = [] if $scope.pages < 3
      return result

    $scope.turnThePage = (i) ->
      if i > 0 and i <= $scope.pages
        $scope.page = i
        #$scope.pagination = getPagination()
        #$scope.paginatedArticles = $scope.articles.slice(($scope.page - 1) * $scope.articlesPerPage, ($scope.page - 1) * $scope.articlesPerPage + $scope.articlesPerPage)

    $scope.setArticlesPerPage(1)