'use strict';

angular.module('cardsOauthApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
