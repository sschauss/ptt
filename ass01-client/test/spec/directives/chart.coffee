'use strict'

describe 'Directive: chart', ->

  # load the directive's module
  beforeEach module 'ass01ClientApp'

  scope = {}

  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()

  it 'should make hidden element visible', inject ($compile) ->
    element = angular.element '<chart></chart>'
    element = $compile(element) scope
    expect(element.text()).toBe 'this is the chart directive'
