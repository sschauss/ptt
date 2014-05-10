'use strict'

angular.module('ass01ClientApp').controller 'NavigationCtrl', ($scope, $location) ->
  $scope.isOnPath = (path) ->
    path == $location.path()