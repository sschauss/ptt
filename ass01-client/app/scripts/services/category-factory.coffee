'use strict'

angular.module('ass01ClientApp')
  .factory 'categoryFactory', ->
    # Service logic
    # ...

    class Category
      constructor: (@label, @key) ->
        @data = []

    # Public API here
    {
      create: (label, key)->
        new Category label, key
    }
