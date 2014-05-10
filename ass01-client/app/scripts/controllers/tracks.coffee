'use strict'

angular.module('ass01ClientApp').controller 'TracksCtrl', ($scope, $resource, categoryFactory) ->
  $scope.categories = [
    categoryFactory.create 'Comments', 'comment_count'
    categoryFactory.create 'Downloads', 'download_count'
    categoryFactory.create 'Playbacks', 'playback_count'
    categoryFactory.create 'Favorites', 'favoritings_count'
  ]

  $scope.chartType = 'polarArea'

  $scope.tracks = []

  Track = $resource '/api/tracks/:id', {id: '@id'}

  currentRequestId = 0

  $scope.addChartData = (track) ->
    $scope.tracks.push track
    for category in $scope.categories
      category.data.push {value: track[category.key]}
    localStorage.setItem 'tracks', JSON.stringify (for track in $scope.tracks
      track.id)
    generateColor $scope.tracks

  $scope.removeChartData = (track) ->
    index = $scope.tracks.indexOf track
    if index > -1
      $scope.tracks.splice index, 1
      for category in $scope.categories
        category.data.splice index, 1
      localStorage.setItem 'tracks', JSON.stringify (for track in $scope.tracks
        track.id)
      generateColor $scope.tracks

  $scope.trackSelected = (track) ->
    for t in $scope.tracks
      if t.id == track.id
        return true
    return false

  $scope.setChartType = (type) ->
    $scope.chartType = type

  $scope.search = (query) ->
    requestId = ++currentRequestId
    if query != ''
      Track.query {q: query}, (response) ->
        if requestId == currentRequestId
          $scope.searchResult = response
    else
      $scope.searchResult = []


  generateColor = (dataSet) ->
    h = 0
    step = 360 / (dataSet.length + 1)
    for i, data of dataSet
      h = (i * step).toFixed 0
      color = "hsl(#{h},70%,35%)"
      data.color = color
      for category in $scope.categories
        category.data[i].color = color

  if (tracks = localStorage.getItem 'tracks') != null
    ids = JSON.parse tracks
    for id in ids
      Track.get {id: id}, (response) ->
        $scope.addChartData response