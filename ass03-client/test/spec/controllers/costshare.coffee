'use strict'

describe 'Controller: CostshareCtrl', ->

  # load the controller's module
  beforeEach module 'ass03ClientApp'

  CostshareCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CostshareCtrl = $controller 'CostshareCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
