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

    rel1 =
      articleId: "1"
      consumerId: "1"
      defrayed: false
    rel2 =
      articleId: "1"
      consumerId: "2"
      defrayed: false
    rel3 =
      articleId: "2"
      consumerId: "1"
      defrayed: false
    rel4 =
      articleId: "2"
      consumerId: "2"
      defrayed: true
    rel5 =
      articleId: "2"
      consumerId: "3"
      defrayed: false
    rel6 =
      articleId: "3"
      consumerId: "1"
      defrayed: false
    rel7 =
      articleId: "3"
      consumerId: "2"
      defrayed: false
    rel8 =
      articleId: "4"
      consumerId: "1"
      defrayed: true
    rel9 =
      articleId: "4"
      consumerId: "2"
      defrayed: true
    rel10 =
      articleId: "5"
      consumerId: "3"
      defrayed: true
    rel11 =
      articleId: "6"
      consumerId: "2"
      defrayed: false
    rel12 =
      articleId: "7"
      consumerId: "1"
      defrayed: true
    rel13 =
      articleId: "7"
      consumerId: "2"
      defrayed: false
    rel14 =
      articleId: "7"
      consumerId: "3"
      defrayed: false
    rel15 =
      articleId: "8"
      consumerId: "1"
      defrayed: false
    rel16 =
      articleId: "8"
      consumerId: "2"
      defrayed: true
    rel17 =
      articleId: "9"
      consumerId: "1"
      defrayed: false
    rel18 =
      articleId: "9"
      consumerId: "2"
      defrayed: false
    rel19 =
      articleId: "10"
      consumerId: "1"
      defrayed: false
    rel20 =
      articleId: "10"
      consumerId: "2"
      defrayed: true

    $scope.articlesUsers = [
      rel1,
      rel2,
      rel3,
      rel4,
      rel5,
      rel6,
      rel7,
      rel8,
      rel9,
      rel10,
      rel11,
      rel12,
      rel13,
      rel14,
      rel15,
      rel16,
      rel17,
      rel18,
      rel19,
      rel20
    ]

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
      id: "1"
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "Brot"
      value: 1.99
      consumers: ["1", "2"]
    article2 =
      id: "2"
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "Apfel"
      value: 1.99
      consumers: ["1", "2", "3"]
    article3 =
      id: "3"
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "Banane"
      value: 1.99
      consumers: ["1", "2"]
    article4 =
      id: "4"
      purchaser: "1"
      purchaseDate: "24.06.2014"
      name: "CD"
      value: 13.99
      consumers: ["1", "2"]
    article5 =
      id: "5"
      purchaser: "3"
      purchaseDate: "25.06.2014"
      name: "Rubin"
      value: 91.50
      consumers: ["3"]
    article6 =
      id: "6"
      purchaser: "3"
      purchaseDate: "25.06.2014"
      name: "Kokosmilch"
      value: 2.89
      consumers: ["2"]
    article7 =
      id: "7"
      purchaser: "3"
      purchaseDate: "25.06.2014"
      name: "Wein"
      value: 7.69
      consumers: ["1", "2", "3"]
    article8 =
      id: "8"
      purchaser: "2"
      purchaseDate: "26.06.2014"
      name: "Grinder"
      value: 7.99
      consumers: ["1", "2"]
    article9 =
      id: "9"
      purchaser: "2"
      purchaseDate: "26.06.2014"
      name: "Gras"
      value: 50.00
      consumers: ["1", "2"]
    article10 =
      id: "10"
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