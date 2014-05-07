'use strict'

angular.module('ass01ClientApp')
  .directive('chart', ->
    templateUrl: 'views/chart.html'
    restrict: 'E'
    scope:
      data: '='
      type: '='
    link: (scope, element) ->

      context = $('.chart', element).get(0).getContext('2d')

      chart = new Chart(context)

      render = () ->
        switch scope.type
          when 'doughnut' then chart.Doughnut(scope.data)
          else chart.PolarArea scope.data

      scope.$watch 'data', (data) ->
        if data
          render()
          console.log data
      , true
  )
