'use strict'

describe 'Controller: ArticlesCtrl', ->

  # load the controller's module
  beforeEach module 'ass03ClientApp'

  ArticlesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    ArticlesCtrl = $controller 'ArticlesCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
