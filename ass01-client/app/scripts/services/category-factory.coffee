'use strict'

angular.module('ass01ClientApp').factory 'categoryFactory', ->
  class Category
    constructor: (@key, @label) ->
      @data = []

  {
  create: (label, key)->
    new Category label, key
  }