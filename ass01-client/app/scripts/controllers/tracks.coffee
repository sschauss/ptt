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

    $scope.getChartData = ->
      Track.get {id: $scope.id}, (response) ->
        $scope.tracks.push response
        $scope.commentGraphData.push {value: response.comment_count}
        $scope.downloadGraphData.push {value: response.download_count}
        $scope.playbackGraphData.push {value: response.playback_count}
        $scope.favoritingsGraphData.push {value: response.favoritings_count}
        generateColor $scope.commentGraphData
        generateColor $scope.downloadGraphData
        generateColor $scope.playbackGraphData
        generateColor $scope.favoritingsGraphData

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
      r = g = b = 0
      for i, data of dataSet
        step = 43331 / (dataSet.length )
        switch i % 3
          when 0 then r = ((r + step) % 256).toFixed 0
          when 1 then g = ((g + step) % 256).toFixed 0
          when 2 then b = ((b + step) % 256).toFixed 0
        data.color =  "rgb(#{r},#{g},#{b})"