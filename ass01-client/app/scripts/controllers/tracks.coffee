'use strict'

angular.module('ass01ClientApp')
  .controller 'TracksCtrl', ($scope, $resource, categoryFactory) ->

    $scope.categories = [
      categoryFactory.create 'Comments', 'comment_count'
      categoryFactory.create 'Downloads', 'download_count'
      categoryFactory.create 'Playbacks', 'playback_count'
      categoryFactory.create 'Favorites', 'favoritings_count'
    ]

    $scope.chartType = 'polarArea'

    $scope.tracks = []

    Track = $resource '/api/tracks/:id', {id: '@id'}

    $scope.addChartData = (track) ->
      $scope.tracks.push track
      for category in $scope.categories
        category.data.push {value: track[category.key]}
      generateColor $scope.tracks

    $scope.removeChartData = (track) ->
      index = $scope.tracks.indexOf track
      if index > -1
        $scope.tracks.splice index, 1
        for category in $scope.categories
          category.data.splice index, 1
        generateColor $scope.tracks

    $scope.trackSelected = (track) ->
      $scope.tracks.indexOf(track) > -1

    $scope.setChartType = (type) ->
      $scope.chartType = type

    $scope.search = (query) ->
      if query
        Track.query {q: query}, (response) ->
          $scope.searchResult = response


    generateColor = (dataSet) ->
      h = 0
      step = 360 / (dataSet.length + 1)
      for i, data of dataSet
        h = (i * step).toFixed 0
        color =  "hsl(#{h},70%,35%)"
        data.color = color
        for category in $scope.categories
          category.data[i].color = color