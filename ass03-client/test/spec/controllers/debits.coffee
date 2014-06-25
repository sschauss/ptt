'use strict'

describe 'Controller: DebitsCtrl', ->

  # load the controller's module
  beforeEach module 'ass03ClientApp'

  DebitsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    DebitsCtrl = $controller 'DebitsCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
