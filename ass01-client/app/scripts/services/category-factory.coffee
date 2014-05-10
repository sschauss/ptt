'use strict'

angular.module('ass01ClientApp').factory 'categoryFactory', ->
  class Category
    constructor: (@label, @key) ->
      @data = []

  {
  create: (label, key)->
    new Category label, key
  }