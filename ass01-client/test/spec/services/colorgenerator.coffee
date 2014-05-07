'use strict'

describe 'Service: Colorgenerator', ->

  # load the service's module
  beforeEach module 'ass01ClientApp'

  # instantiate service
  Colorgenerator = {}
  beforeEach inject (_Colorgenerator_) ->
    Colorgenerator = _Colorgenerator_

  it 'should do something', ->
    expect(!!Colorgenerator).toBe true
