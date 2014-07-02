'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:CostshareIdCtrl
 # @description
 # # CostshareIdCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'CostshareIdCtrl', ($scope, $resource, $routeParams) ->

    #TODO get actual json from server
    Users = $resource '/api/costshare/'+$routeParams.id+'users'
    #$scope.users = Users.query()
    Articles = $resource '/api/articles/' #TODO kann man hier über die costshare id filtern?
    #$scope.articles = Articles.query()
    ArticlesUsers = $resource '/api/article_user/' #TODO kann man hier über die costshare id filtern?
    #$scope.articlesUsers = ArticlesUsers.query()

    $scope.getPurchaser = (purchaserId) ->
      for user in $scope.users
        return user.firstName + " " + user.lastName if user.id == purchaserId
      return "Couldn't find username"

    $scope.isConsumer = (article, userId) ->
      for consumerId in article.consumers
        return true if consumerId == userId
      false

    $scope.isDefrayed = (article, user) ->
      return true if jQuery.inArray(article.id, user.defrayedArticles) != -1

    calculateDebts = (users, articles) ->
      initializeDebts(users)
      for article in articles
        for consumerId in article.consumers
          consumer = getUser(consumerId)
          samePerson = consumerId == article.purchaser
          if !samePerson and !$scope.isDefrayed(article, consumer)
            consumer.debts.overall += round(article.value / article.consumers.length, 2)
            consumer.debts[article.purchaser] += round(article.value / article.consumers.length, 2)
      fixFloatingPointBug(users) #TODO ist das jetzt wirklich gelöst?

    initializeDebts = (users) ->
      for user in users
        user.debts = {}
        user.debts.overall = 0
        for deptee in users
          user.debts[deptee.id] = 0

    getUser = (userId) ->
      try
        for user in $scope.users
          return user if user.id == userId
        throw new Error("User not found")
      catch
        alert("We encountered some serious problems - sorry!")

    round = (number, decimals) ->
      return Math.round(number * Math.pow(10, decimals)) / Math.pow(10, decimals)

    #addition of already rounded numbers sometimes creates values like 12.45000000000000001
    fixFloatingPointBug = (users) ->
      for user in users
        user.debts.overall = round(user.debts.overall, 2)

    setDefrayedArticles = (users, articlesUsers) ->
      initializeDefrayedArticles(users)
      for articleUser in articlesUsers
        user = getUser(articleUser.consumerId)
        user.defrayedArticles.push(articleUser.articleId) if articleUser.defrayed == true

    initializeDefrayedArticles = (users) ->
      for user in users
        user.defrayedArticles = []

    setDefrayedArticles($scope.users, $scope.articlesUsers)
    calculateDebts($scope.users, $scope.articles)