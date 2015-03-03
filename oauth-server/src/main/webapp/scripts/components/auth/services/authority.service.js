'use strict';

angular.module('cardsOauthApp').factory('Authority', function($resource) {
	
    return $resource('api/authorities/:name', {}, {
    	
        'findAll': { method: 'GET', isArray: true }

    });	
});