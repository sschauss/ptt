'use strict'

describe 'Controller: CostshareIdCtrl', ->

  # load the controller's module
  beforeEach module 'ass03ClientApp'

  CostshareIdCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CostshareIdCtrl = $controller 'CostshareIdCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
