'use strict';

angular.module('cardsOauthApp')
    .factory('AuthServerProvider', function loginService($http, $state, localStorageService, Base64) {
        return {
            login: function(credentials) {
            	
                var formData = "username=" + credentials.username + "&password="
                    + credentials.password + "&grant_type=password";
                
                var request = {
                		method: 'POST',
                		url: '/uaa/oauth/token',
                		headers: {
                			'Authorization': 'Basic cmVhZGluZXNzLWNlbnRlcjpyY3NlY3JldA==',
                			'Content-Type': 'application/x-www-form-urlencoded'
                		},
                		data: formData
                }
                
                return $http(request).success(function (response) {
                    var expiredAt = new Date();
                    expiredAt.setSeconds(expiredAt.getSeconds() + response.expires_in);
                    response.expires_at = expiredAt.getTime();
                    localStorageService.set('token', response);
                    return response;
                });
            },
            logout: function() {
                // logout from the server
                $http.post('api/logout').then(function() {
                    localStorageService.clearAll();
                    $state.go('login');
                });
            },
            getToken: function () {
                return localStorageService.get('token');
            },
            hasValidToken: function () {
                var token = this.getToken();
                return token && token.expires_at && token.expires_at > new Date().getTime();
            }
        };
    });
