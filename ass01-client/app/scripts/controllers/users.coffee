'use strict'

angular.module('ass01ClientApp')
  .controller 'UsersCtrl', ($scope, $resource) ->
    $scope.categories = [
      {
        label: 'Tracks'
        key: 'track_count'
      }
      {
        label: 'Playlists'
        key: 'playlist_count'
      }
      {
        label: 'Followers'
        key: 'followers_count'
      }
      {
        label: 'Followings'
        key: 'followings_count'
      }
      {
        label: 'Favorites'
        key: 'public_favorites_count'
      }
    ]

    $scope.query = ''

    $scope.chartType = 'polarArea'

    $scope.tracksGraphData = []

    $scope.playlistsGraphData = []

    $scope.followersGraphData = []

    $scope.followingsGraphData = []

    $scope.favoritesGraphData = []

    $scope.users = []

    User = $resource '/api/users/:id', {id: '@id'}

    $scope.addChartData = (user) ->
      $scope.users.push user
      $scope.tracksGraphData.push {value: user.track_count}
      $scope.playlistsGraphData.push {value: user.playlist_count}
      $scope.followersGraphData.push {value: user.followers_count}
      $scope.followingsGraphData.push {value: user.followings_count}
      $scope.favoritesGraphData.push {value: user.public_favorites_count}
      generateColor $scope.users

    $scope.removeChartData = (user) ->
      index = $scope.users.indexOf(user)
      if index > -1
        $scope.users.splice index, 1
        $scope.tracksGraphData.splice index, 1
        $scope.playlistsGraphData.splice index, 1
        $scope.followersGraphData.splice index, 1
        $scope.followingsGraphData.splice index, 1
        $scope.favoritesGraphData.splice index, 1
        generateColor $scope.users


    $scope.userSelected = (user) ->
      $scope.users.indexOf(user) > -1

    $scope.setChartType = (type) ->
      $scope.chartType = type

    $scope.$watch 'query', (query) ->
      if query == ''
        $scope.searchResult = []
      else
        search query

    search = ->
      if $scope.query
        User.query {q: $scope.query}, (response) ->
          $scope.searchResult = response


    generateColor = (dataSet) ->
      h = 0
      step = 360 / (dataSet.length + 1)
      for i, data of dataSet
        h = (i * step).toFixed 0
        color =  "hsl(#{h},70%,35%)"
        data.color = color
        $scope.users[i].color =  color
        $scope.tracksGraphData[i].color = color
        $scope.playlistsGraphData[i].color = color
        $scope.followersGraphData[i].color = color
        $scope.followingsGraphData[i].color = color
        $scope.favoritesGraphData[i].color = color
