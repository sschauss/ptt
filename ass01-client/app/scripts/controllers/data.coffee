'use strict'

angular.module('ass01ClientApp').controller 'DataCtrl', ($scope, $resource, categoryFactory, options) ->
  $scope.categories = for category in options.categories
    categoryFactory.create category.key, category.label

  $scope.chartType = 'polarArea'

  $scope.entities = []

  Entity = $resource options.resourceUrl

  entityId = options.entityId

  currentRequestId = 0

  $scope.chartColClass = (categories) ->
    col = Math.floor 12 / categories.length
    'col-md-' + col

  $scope.chartOffsetClass = ->
    offset = Math.floor (12 % $scope.categories.length) / 2
    if offset > 0
      'col-md-' + offset
    else
      ''

  $scope.addChartData = (entity) ->
    $scope.entities.push entity
    forEachIn $scope.categories, (category) ->
      category.data.push {value: entity[category.key]}
    localStorage.setItem entityId, JSON.stringify (for entity in $scope.entities
      entity.id)
    generateColor $scope.entities

  $scope.removeChartData = (entity) ->
    index = $scope.entities.indexOf entity
    if index > -1
      $scope.entities.splice index, 1
      forEachIn $scope.categories, (category) ->
        category.data.splice index, 1
      localStorage.setItem entityId, JSON.stringify (for entity in $scope.entities
        entity.id)
      generateColor $scope.entities

  $scope.entitySelected = (entity) ->
    foeEachIn $scope.entities, e ->
      if e.id == entity.id
        return true
    return false

  $scope.setChartType = (chartType) ->
    $scope.chartType = chartType
    localStorage.setItem entityId + 'options', JSON.stringify {chartType: chartType}

  $scope.search = (query) ->
    requestId = ++currentRequestId
    if query != ''
      Entity.query {q: query}, (response) ->
        if requestId == currentRequestId
          $scope.searchResult = response
    else
      $scope.searchResult = []

  generateColor = (dataSet) ->
    h = 0
    step = 360 / (dataSet.length + 1)
    forEachOf dataSet, (i, data) ->
      h = (i * step).toFixed 0
      color = "hsl(#{h},70%,35%)"
      data.color = color
      for category in $scope.categories
        category.data[i].color = color

  forEachIn = (array, action) ->
    for e in array
      action e

  forEachOf = (array, action) ->
    for i, e of array
      action i, e

  if (options = localStorage.getItem entityId + 'options') != null
    $scope.setChartType (JSON.parse options).chartType

  if (entities = localStorage.getItem entityId) != null
    ids = JSON.parse entities
    forEachIn ids, (id) ->
      Entity.get {id: id}, (response) ->
        $scope.addChartData response

