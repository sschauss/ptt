'use strict'

angular.module('ass01ClientApp')
  .controller 'TracksCtrl', ($scope) ->
    $scope.categories = [
      'Downloads'
      'Playbacks'
    ]
