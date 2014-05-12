'use strict'

angular.module('ass01ClientApp').controller 'TracksCtrl', ($controller, $scope, $resource, categoryFactory) ->
  $controller 'DataCtrl',
    $scope: $scope
    $resource: $resource
    categoryFactory: categoryFactory
    options:
      resourceUrl: '/api/tracks/:id'
      entityId: 'tracks'
      categories: [
        {
          key: 'commentCount'
          label: 'Comments'
        }
        {
          key: 'downloadCount'
          label: 'Downloads'
        }
        {
          key: 'playbackCount'
          label: 'Playbacks'}
        {
          key: 'favoritingsCount'
          label: 'Favorites'
        }
        {
          key: 'interestingness'
          label: 'Interestingness'
        }
      ]