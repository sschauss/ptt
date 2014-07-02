'use strict'

describe 'Controller: CostsharesCtrl', ->

  # load the controller's module
  beforeEach module 'ass03ClientApp'

  CostsharesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CostsharesCtrl = $controller 'CostsharesCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
