'use strict';

angular.module('cardsOauthApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


