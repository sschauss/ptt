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
      name: "Kiste"
      value: 7.99
      consumers: ["1", "2"]
    article9 =
      id: "9"
      purchaser: "2"
      purchaseDate: "26.06.2014"
      name: "Bonbons"
      value: 50.00
      consumers: ["1", "2"]
    article10 =
      id: "10"
      purchaser: "2"
      purchaseDate: "26.06.2014"
      name: "Kalender"
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
    #Users = $resource '/api/costshare/'+$routeParams.id+'users'
    #$scope.users = Users.query()
    #Articles = $resource '/api/articles/' #TODO kann man hier über die costshare id filtern?, id's müssen mitgegeben werden (stand auf blatt nicht)
    #$scope.articles = Articles.query()
    #ArticlesUsers = $resource '/api/article_user/' #TODO kann man hier über die costshare id filtern?
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

    $scope.calculationMethod = "Standard"

    $scope.switchCalculationMethod = (method) ->
      switch method
        when "Standard" then $scope.calculationMethod = "Standard"
        when "Offsetted" then $scope.calculationMethod = "Offsetted"
        when "Fully Offsetted" then $scope.calculationMethod = "Fully Offsetted"
        else $scope.calculationMethod = "Standard"

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
        $scope.pagination = getPagination()
        $scope.paginatedArticles = $scope.articles.slice(($scope.page - 1) * $scope.articlesPerPage, ($scope.page - 1) * $scope.articlesPerPage + $scope.articlesPerPage)

    calculateDebts = (users, articles) ->
      initializeDebts(users)
      for article in articles
        for consumerId in article.consumers
          consumer = getUser(consumerId)
          samePerson = consumerId == article.purchaser
          if !samePerson and !$scope.isDefrayed(article, consumer)
            consumer.debtsOverall += round(article.value / article.consumers.length, 2)
            consumer.debts[article.purchaser] += round(article.value / article.consumers.length, 2)

    calculateOffsettedDebts = (users) ->
      initializeOffsettedDebts(users)
      for user in users
        for debtee in users
          if user.debts[debtee.id] >= debtee.debts[user.id]
            user.offsettedDebts[debtee.id] = user.debts[debtee.id] - debtee.debts[user.id]
          else
            debtee.offsettedDebts[user.id] = debtee.debts[user.id] - user.debts[debtee.id]
          user.offsettedDebtsOverall += user.offsettedDebts[debtee.id]

    initializeDebts = (users) ->
        for user in users
          user.debts = {}
          user.debtsOverall = 0
          for debtee in users
            user.debts[debtee.id] = 0

    initializeOffsettedDebts = (users) ->
      for user in users
        user.offsettedDebts = {}
        user.offsettedDebtsOverall = 0
        for debtee in users
          user.offsettedDebts[debtee.id] = 0

    initializeFullyOffsettedDebts = (users) ->
      for user in users
        user.fullyOffsettedDebts = {}
        user.fullyOffsettedDebtsOverall = 0
        for debtee in users
          user.fullyOffsettedDebts[debtee.id] = 0

    initializeGetsOrPays = (users) ->
      for user in users
        user.getsOrPays = 0

    calculateGetsOrPays = (users) ->
      initializeGetsOrPays(users)
      for user in users
        for debteeId, debt of user.offsettedDebts
          user.getsOrPays -= debt
          debtee = getUser(debteeId)
          debtee.getsOrPays += debt

    calculateFullyOffsettedDebts = (users) -> #TODO not trivial - we should test that with more data
      calculateGetsOrPays(users)
      initializeFullyOffsettedDebts(users)
      for debtor in users
        if debtor.getsOrPays < 0
          for debtee in users
            if debtee.id != debtor.id and debtee.getsOrPays > 0
              if Math.abs(debtor.getsOrPays) >= Math.abs(debtee.getsOrPays)
                debtor.fullyOffsettedDebts[debtee.id] += debtee.getsOrPays
                debtor.fullyOffsettedDebtsOverall += debtee.getsOrPays
                debtor.getsOrPays += debtee.getsOrPays
                debtee.getsOrPays = 0
              else
                debtor.fullyOffsettedDebts[debtee.id] += Math.abs(debtor.getsOrPays)
                debtor.fullyOffsettedDebtsOverall += Math.abs(debtor.getsOrPays)
                debtee.getsOrPays += debtor.getsOrPays
                debtor.getsOrPays = 0

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
    fixFloatingPointBug = (users) -> #TODO no solution - instead we should use something like bignumber.js with safe arithmetics for all values
      for user in users
        user.debtsOverall = round(user.debtsOverall, 2)
        user.offsettedDebtsOverall = round(user.offsettedDebtsOverall, 2)

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
    calculateOffsettedDebts($scope.users)
    fixFloatingPointBug($scope.users)
    calculateFullyOffsettedDebts($scope.users)
    $scope.setArticlesPerPage(5)
    $scope.turnThePage(1)

#TODO if consumer == purchaser: do we initiate the database entry as defrayed? then the samePerson() stuff would be redunand
  #TODO initialize methods in one loop