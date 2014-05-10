'use strict'

angular.module('ass01ClientApp').directive('chart', ->
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
        when 'pie' then chart.Pie scope.data
        when 'doughnut' then chart.Doughnut scope.data
        when 'polarArea' then chart.PolarArea scope.data
        else
          chart.Pie scope.data

    scope.$watch '[data, type]', (data) ->
      if data != []
        render()
    , true
)