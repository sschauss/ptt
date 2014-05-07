'use strict'

angular.module('ass01ClientApp')
  .controller 'TracksCtrl', ($scope, $resource) ->
    $scope.categories = [
      'Downloads'
      'Playbacks'
    ]

    $scope.commentsData = []

    GraphData = $resource '/api/tracks/graphs/:id', {id: '@id'}

    $scope.getChartData = ->
      GraphData.get {id: $scope.trackId}, (response) ->
        $scope.commentsData.push {value: response.commentsCount, color: 'red'}