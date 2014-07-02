'use strict'

describe 'Controller: CostsharesEntityidCtrl', ->

  # load the controller's module
  beforeEach module 'ass03ClientApp'

  CostsharesEntityidCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CostsharesEntityidCtrl = $controller 'CostsharesEntityidCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
