'use strict';

angular.module('cardsOauthApp').factory('OAuth', function($resource) {
	
    return $resource('api/oauth/clients/:clientId', {}, {
    	
    	'findOneClient': { method: 'GET', isArray: false },
        'findAllClients': { method: 'GET', isArray: true },                     
        'updateClient': { method: 'PUT' },
        'removeClient': { method: 'DELETE' }
    });	
});