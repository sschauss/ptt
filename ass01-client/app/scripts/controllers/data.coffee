'use strict'

angular.module('ass01ClientApp').controller 'DataCtrl', ($scope, $resource, categoryFactory, options) ->
  $scope.categories = []

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
    for category in $scope.categories
      category.data.push {value: entity[category.key]}
    localStorage.setItem entityId, JSON.stringify (for entity in $scope.entities
      entity.id)
    generateColor $scope.entities

  $scope.removeChartData = (entity) ->
    index = $scope.entities.indexOf entity
    if index > -1
      $scope.entities.splice index, 1
      for category in $scope.categories
        category.data.splice index, 1
      localStorage.setItem entityId, JSON.stringify (for entity in $scope.entities
        entity.id)
      generateColor $scope.entities

  $scope.entitySelected = (entity) ->
    for e in $scope.entities
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
    console.log 'generate'
    h = 0
    step = 360 / (dataSet.length + 1)
    for i, data of dataSet
      h = (i * step).toFixed 0
      color = "hsl(#{h},70%,35%)"
      data.color = color
      for category in $scope.categories
        category.data[i].color = color

  for category in options.categories
    $scope.categories.push categoryFactory.create(category.key, category.label)

  if (options = localStorage.getItem entityId + 'options') != null
    $scope.setChartType (JSON.parse options).chartType

  if (entities = localStorage.getItem entityId) != null
    ids = JSON.parse entities
    for id in ids
      Entity.get {id: id}, (response) ->
        $scope.addChartData response