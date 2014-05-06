'use strict'

describe 'Controller: TracksCtrl', ->

  # load the controller's module
  beforeEach module 'ass01ClientApp'

  TracksCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    TracksCtrl = $controller 'TracksCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
