'use strict'

angular.module('ass01ClientApp')
  .directive('modal', ->
    templateUrl: 'views/modal.html'
    restrict: 'E'
    transclude: true
    scope: {
      id: '=ngId'
      title: '='
    }
  )
