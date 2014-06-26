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

    user1 =
      id: "1"
      firstName: "Elias"
      lastName: "Schmitt"

    user2 =
      id: "2"
      firstName: "Felix"
      lastName: "Engelmann"
    user3 =
      id: "3"
      firstName: "Sara"
      lastName: "Lindlein"

    $scope.users = [
      user1,
      user2,
      user3
    ]

    article1 =
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "Brot"
      value: 1.99
      consumers: ["1", "2"]
    article2 =
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "Apfel"
      value: 1.99
      consumers: ["1", "2"]
    article3 =
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "Banane"
      value: 1.99
      consumers: ["1", "2"]
    article4 =
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "CD"
      value: 13.99
      consumers: ["1", "2"]
    article5 =
      purchaser: "3"
      purchaseDate: "25.06.2014"
      name: "Rubin"
      value: 91.50
      consumers: ["3"]
    article6 =
      purchaser: "3"
      purchaseDate: "25.06.2014"
      name: "Kokosmilch"
      value: 2.89
      consumers: ["2"]
    article7 =
      purchaser: "3"
      purchaseDate: "25.06.2014"
      name: "Wein"
      value: 7.69
      consumers: ["1", "2", "3"]
    article8 =
      purchaser: "2"
      purchaseDate: "26.06.2014"
      name: "Grinder"
      value: 7.99
      consumers: ["1", "2"]
    article9 =
      purchaser: "2"
      purchaseDate: "26.06.2014"
      name: "Gras"
      value: 50.00
      consumers: ["1", "2"]
    article10 =
      purchaser: "2"
      purchaseDate: "26.06.2014"
      name: "Papes"
      value: 0.99
      consumers: ["1", "2"]

    $scope.articles = [
      article1,
      article2,
      article3,
      article4,
      article5,
      article6,
      article7,
      article8,
      article9,
      article10
    ]

    #TODO use server json
    Users = $resource '/api/costshare/'+$routeParams.id+'users'
    #$scope.users = Users.query()
    Articles = $resource '/api/articles/' #TODO kann man hier über die costshare id filtern?
    #$scope.articles = Articles.query()

    $scope.getPurchaser = (purchaserId) ->
      for user in $scope.users
        return user.firstName + " " + user.lastName if user.id == purchaserId

    $scope.isConsumer = (article, userId) ->
      for consumerId in article.consumers
        return true if consumerId == userId
      false

    calculateDebts = (users, articles) ->
      initializeDebts(users)
      for article in articles
        for consumerId in article.consumers
          consumer = getUser(consumerId)
          consumer.debts.overall += round(article.value / article.consumers.length, 2)
          consumer.debts[article.purchaser] += round(article.value / article.consumers.length, 2)

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

    calculateDebts($scope.users, $scope.articles)