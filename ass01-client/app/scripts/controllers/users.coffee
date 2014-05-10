'use strict'

angular.module('ass01ClientApp').controller 'UsersCtrl', ($scope, $resource, categoryFactory) ->
  $scope.categories = [
    categoryFactory.create 'Tracks', 'track_count'
    categoryFactory.create 'Playlists', 'playlist_count'
    categoryFactory.create 'Followers', 'followers_count'
    categoryFactory.create 'Followings', 'followings_count'
    categoryFactory.create 'Favorites', 'public_favorites_count'
  ]

  $scope.query = ''

  $scope.chartType = 'polarArea'

  $scope.users = []

  User = $resource '/api/users/:id', {id: '@id'}

  currentRequestId = 0

  $scope.addChartData = (user) ->
    $scope.users.push user
    for category in $scope.categories
      category.data.push {value: user[category.key]}
    localStorage.setItem 'users', JSON.stringify (for user in $scope.users
      user.id)
    generateColor $scope.users

  $scope.removeChartData = (user) ->
    index = $scope.users.indexOf user
    if index > -1
      $scope.users.splice index, 1
      for category in $scope.categories
        category.data.splice index, 1
      localStorage.setItem 'users', JSON.stringify (for user in $scope.users
        user.id)
      generateColor $scope.users


  $scope.userSelected = (user) ->
    for u in $scope.users
      if u.id == user.id
        return true
    return false

  $scope.setChartType = (type) ->
    $scope.chartType = type

  $scope.search = (query) ->
    requestId = ++currentRequestId
    if query != ''
      User.query {q: query}, (response) ->
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

  if (users = localStorage.getItem 'users') != null
    ids = JSON.parse users
    for id in ids
      User.get {id: id}, (response) ->
        $scope.addChartData response