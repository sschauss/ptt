'use strict'

angular.module('ass01ClientApp')
  .controller 'TracksCtrl', ($scope, $resource) ->
    $scope.categories = [
      'Downloads'
      'Playbacks'
    ]

    $scope.trackId = null

    $scope.commentsData = []

    GraphData = $resource '/api/tracks/:id', {id: '@id'}

    $scope.getChartData = ->
      GraphData.get {id: $scope.trackId}, (response) ->
        $scope.commentsData.push {value: response.comment_count}
        generate($scope.commentsData)

    generate = (dataSet) ->
      r = 0
      g = 0
      b = 0
      for i, data of dataSet
        step = 43331 / (dataSet.length )
        switch i % 3
          when 0 then r = ((r + step) % 256).toFixed 0
          when 1 then g = ((g + step) % 256).toFixed 0
          when 2 then b = ((b + step) % 256).toFixed 0
        data.color =  "rgb(#{r},#{g},#{b})"