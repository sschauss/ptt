'use strict'

angular.module('ass01ClientApp')
  .directive('chart', ->
    templateUrl: 'views/chart.html'
    restrict: 'E'
    scope:
      data: '='
    link: (scope, element, attrs) ->
      context = $('.chart', element).get(0).getContext('2d')
      chart = new Chart(context).Doughnut(scope.data)

  )
