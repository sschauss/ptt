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



    getDebts = (users) ->
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