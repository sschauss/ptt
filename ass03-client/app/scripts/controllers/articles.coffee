'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:ArticlesCtrl
 # @description
 # # ArticlesCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'ArticlesCtrl', ($scope) ->

    #TODO get JSON users from server
    user1 =
      firstName: "Elias"
      lastName: "Schmitt"
      debits: [0, 12.56, 43.98]
    user2 =
      firstName: "Felix"
      lastName: "Engelmann"
      debits: [7.34, 0, 13.78]
    user3 =
      firstName: "Sara"
      lastName: "Lindlein"
      debits: [3, 21.56, 0]

    $scope.users = [
      user1,
      user2,
      user3
    ]

    #TODO get JSON articles from server
    article1 =
      purchaser: user1
      date: "24.06.2014"
      name: "Brot"
      value: "1.99"
      consumers: [true, true, true]
    article2 =
      purchaser: user1
      date: "24.06.2014"
      name: "Bier"
      value: "14.99"
      consumers: [true, true, false]
    article3 =
      purchaser: user2
      date: "24.06.2014"
      name: "Klopapier"
      value: "1.89"
      consumers: [true, true, true]
    article4 =
      purchaser: user1
      date: "24.06.2014"
      name: "Auto"
      value: "1500.00"
      consumers: [true, true, true]
    article5 =
      purchaser: user3
      date: "25.06.2014"
      name: "Rosine"
      value: "0.01"
      consumers: [true, false, false]
    article6 =
      purchaser: user3
      date: "25.06.2014"
      name: "Wassermelone"
      value: "5.49"
      consumers: [true, false, true]
    article7 =
      purchaser: user3
      date: "25.06.2014"
      name: "Tischdecke"
      value: "12.99"
      consumers: [true, true, true]

    $scope.articles = [
      article1,
      article2,
      article3,
      article4,
      article5,
      article6,
      article7
    ]
