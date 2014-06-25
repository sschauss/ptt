'use strict'

###*
 # @ngdoc function
 # @name ass03ClientApp.controller:DebitsCtrl
 # @description
 # # DebitsCtrl
 # Controller of the ass03ClientApp
###
angular.module('ass03ClientApp')
  .controller 'DebitsCtrl', ($scope) ->

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
