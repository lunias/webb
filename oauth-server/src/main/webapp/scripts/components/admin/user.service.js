'use strict';

angular.module('cardsOauthApp').factory('User', function($resource) {
	
    return $resource('api/users/:username', {}, {
    	
        'findAll': { method: 'GET', isArray: false,
        	interceptor: {
                response: function(response) {
                    return {                    	
                    	body: response.data._embedded.userResourceList,
                    	links: response.data._links,
                    	page: response.data.page
                    };
                }        		
        	}
        },
        
        'findAllClients': { method: 'GET', isArray: true,
        	url: 'api/users/:username/clients' 
        },
        
        'findMe': { method: 'GET', isArray: false,
        	url: 'api/users/me'
        },
        
        'updateMe': { method: 'PUT', isArray:false,
        	url: 'api/users/me'
        },
        
        'update': { method: 'PUT' },
        'remove': { method: 'DELETE' }
    });	
});