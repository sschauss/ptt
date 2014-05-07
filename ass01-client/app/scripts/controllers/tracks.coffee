'use strict'

angular.module('ass01ClientApp')
  .controller 'TracksCtrl', ($scope, $resource) ->
    $scope.categories = [
      {
        label: 'Comments'
        key: 'comment_count'
      }
      {
        label: 'Downloads'
        key: 'download_count'
      }
      {
        label: 'Playbacks'
        key: 'playback_count'
      }
      {
        label: 'Favorites'
        key: 'favoritings_count'
      }
    ]

    $scope.id = ''

    $scope.query = ''

    $scope.commentGraphData = []

    $scope.downloadGraphData = []

    $scope.playbackGraphData = []

    $scope.favoritingsGraphData = []

    $scope.tracks = []

    Track = $resource '/api/tracks/:id', {id: '@id'}

    $scope.addChartData = (track) ->
      $scope.tracks.push track
      $scope.commentGraphData.push {value: track.comment_count}
      $scope.downloadGraphData.push {value: track.download_count}
      $scope.playbackGraphData.push {value: track.playback_count}
      $scope.favoritingsGraphData.push {value: track.favoritings_count}
      generateColor $scope.tracks

    $scope.removeChartData = (track) ->
      index = $scope.tracks.indexOf(track)
      if index > -1
        $scope.tracks.splice index, 1
        $scope.commentGraphData.splice index, 1
        $scope.downloadGraphData.splice index, 1
        $scope.playbackGraphData.splice index, 1
        $scope.favoritingsGraphData.splice index, 1


    $scope.trackSelected = (track) ->
      $scope.tracks.indexOf(track) > -1

    $scope.$watch 'query', (query) ->
      if query == ''
        $scope.searchResult = []
      else
        search query

    search = ->
      if $scope.query
        Track.query {q: $scope.query}, (response) ->
          $scope.searchResult = response


    generateColor = (dataSet) ->
      h = 0
      step = 360 / (dataSet.length + 1)
      for i, data of dataSet
        h = (i * step).toFixed 0
        color =  "hsl(#{h},70%,35%)"
        data.color = color
        $scope.tracks[i].color =  color
        $scope.commentGraphData[i].color = color
        $scope.downloadGraphData[i].color = color
        $scope.playbackGraphData[i].color = color
        $scope.favoritingsGraphData[i].color = color