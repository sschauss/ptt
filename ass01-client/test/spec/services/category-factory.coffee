'use strict'

describe 'Service: categoryFactory', ->

  # load the service's module
  beforeEach module 'ass01ClientApp'

  # instantiate service
  categoryFactory = {}
  beforeEach inject (_categoryFactory_) ->
    categoryFactory = _categoryFactory_

  it 'should do something', ->
    expect(!!categoryFactory).toBe true
