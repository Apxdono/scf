var _t = require('../tools.js');
var utils = require('../utils.js');
module.exports = /*@ngInject*/ UserService;
function UserService($q, $http, $rootScope, authService, NotificatorService) {
    'use strict';
    var _user,
        triedLoading = false,
        service = {
            ready: false,
            user: function () {
                return user();
            },
            login: function (username, password) {
                $http({
                    method: 'POST',
                    url: '/login',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: {
                        username: username,
                        password: password
                    },
                    transformRequest: utils.transformRequest
                }).then(authService.loginConfirmed, loadUser);
            },
            logout: function () {
                $http({
                    method: 'GET',
                    url: '/logout'
                }).finally(function () {
                    user(undefined);
                    loadUser();
                });
            }
        };



    //Get/set user function
    function user(u) {
        if (arguments.length > 0) {
            _user = u;
            service.ready = !_t.ng.isUndefined(u);
            NotificatorService.notify.apply(service, ['user', _user]);
        }
        return _user;
    }

    //loading of a user
    function loadUser() {
        if(triedLoading) {
            return;
        }
        triedLoading = true;
        $http({
            method: 'GET',
            url: '/profile'
        }).then(function (response) {
            user(response.data);
            triedLoading = false;
        },function(){
            $rootScope.$broadcast(_t.constants.event.login.required);
        });
    }

    //Post init
    var toDestroy = [];
    toDestroy.push($rootScope.$on(_t.constants.event.login.confirmed,loadUser));
    toDestroy.push($rootScope.$on(_t.constants.event.login.required,loadUser));
    $rootScope.$on(_t.constants.event.scope.destroy,function(){
        toDestroy.forEach(function(cb){
            cb();
        });
    });
    $rootScope.$broadcast(_t.constants.event.login.required);

    return NotificatorService.addNature(service);
}
