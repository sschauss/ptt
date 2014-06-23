'use strict'

describe 'Controller: FlatsharesCtrl', ->

  # load the controller's module
  beforeEach module 'ass03ClientApp'

  FlatsharesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    FlatsharesCtrl = $controller 'FlatsharesCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
