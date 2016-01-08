var utils = require('../utils.js');
var angular = require('../angular-shim.js');
module.exports = /*@ngInject*/ UserService;
function UserService($q,$http,authService,NotificatorService){
    'use strict';
    var _user, triedLoading = false, service = {};

    function user(u){
        if(arguments.length > 0){
            _user = u;
            service.ready = !angular.isUndefined(u);
            NotificatorService.notify.apply(service,['user',_user]);
        }
        return _user;
    }

    service.ready = false;
    service.user = function(){
        return user();
    };
    service.login = function(username,password){
        $http({
            method : 'POST',
            url : '/login',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data : {
                username : username,
                password : password
            },
            transformRequest : utils.transformRequest
        }).then(authService.loginConfirmed,loadUser);
    };

    service.logout = function(){
        $http({
            method:'GET',
            url : '/logout'
        }).then(function(){
            user(undefined);
            loadUser();
        });
    };

    function loadUser(){
        triedLoading = true;
        $http({
            method : 'GET',
            url : '/profile'
        }).then(function(response){
            user(response.data);
            triedLoading = false;
        });
    }

    loadUser();
    return NotificatorService.addNature(service);
}
