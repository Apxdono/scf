define([
    'require',
    'angular',
    'angular-http-auth'
], function (r, ng) {

    function loginController($scope, $log, $http) {
        $scope.credentials = {};
        $scope.login = function () {
            $http({
                method: 'POST',
                url: '/login',
                data : $scope.credentials,
                headers : {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                }
            }).then(function (response) {
                $log.info('login-success', response);
            },function (reject) {
                $log.info('login-error', reject);
            })
        }
    }

    loginController.$inject = ['$scope', '$log', '$http'];

    var mod = ng.module('app.services', ['http-auth-interceptor']);
    mod.controller('LoginCtrl', loginController);
    function $config($provide) {
        mod.register = {
            service: $provide.service,
            factory: $provide.factory
        };
    }

    $config.$inject = ["$provide"];
    mod.config($config);
    return mod;
});
