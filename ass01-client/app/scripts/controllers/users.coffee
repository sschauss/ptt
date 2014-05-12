'use strict'

angular.module('ass01ClientApp').controller 'UsersCtrl', ($controller, $scope, $resource, categoryFactory) ->

  $controller 'DataCtrl',
    $scope: $scope
    $resource: $resource
    categoryFactory: categoryFactory
    options:
      resourceUrl: '/api/users/:id'
      entityId: 'users'
      categories: [
        {
          key: 'trackCount'
          label: 'Tracks'
        }
        {
          key: 'playlistCount'
          label: 'Playlists'
        }
        {
          key: 'followersCount'
          label: 'Followers'}
        {
          key: 'followingsCount'
          label: 'Followings'
        }
        {
          key: 'publicFavoritesCount'
          label: 'Favorites'
        }
      ]