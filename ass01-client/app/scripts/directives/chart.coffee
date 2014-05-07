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

      render = () ->
        chart = new Chart(context)
        switch scope.type
          when 'pie' then chart.Pie(scope.data)
          when 'doughnut' then chart.Doughnut(scope.data)
          when 'bar' then chart.Bar(scope.data)
          else chart.PolarArea scope.data

      scope.$watch 'data', (data) ->
        if data != []
          console.log data
          render()
          console.log data
      , true
  )
