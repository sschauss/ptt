'use strict'

angular.module('ass01ClientApp')
  .directive('chart', ->
    template: '<div></div>'
    restrict: 'E'
    link: (scope, element, attrs) ->
      element.text 'this is the chart directive'
  )
