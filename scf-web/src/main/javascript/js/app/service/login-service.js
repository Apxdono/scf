define([
    'require',
    'angular',
    './service-module'
], function (r, ng, mod) {

    function LoginService($log, $rootScope, $http,$timeout, appevent, authService) {
        var s = {};

        var lasttimeout = ng.noop;
        function flush() {
            s.user = {};
            s.isLoggedIn = false;
            $timeout.cancel(lasttimeout);
            lasttimeout = $timeout(function(){
                s.pending = false;
            },1000);
        }

        function fetchProfile() {
            $http({
                method: 'GET',
                url: '/profile'
            }).then(function (res) {
                $log.info('login-success', res);
                s.user = res.data;
                s.isLoggedIn = true;
                s.pending = false;
            }, flush);
        };

        s.login = function (data) {
            s.pending = true;
            $http({
                method: 'POST',
                url: '/login',
                data: data,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: commons.transformRequest
            }).then(authService.loginConfirmed, fetchProfile)
        };

        s.logout  = function () {
            s.pending = true;
            $http({
                method: 'POST',
                url: '/logout',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: commons.transformRequest
            }).then(fetchProfile, fetchProfile)
        };

        $rootScope.$on(appevent.LOGIN_REQUIRED, flush);
        $rootScope.$on(appevent.LOGIN_ERROR, flush);
        fetchProfile();
        return s;
    }

    LoginService.$inject = ['$log', '$rootScope', '$http','$timeout', 'APP_EVENT', 'authService'];
    (mod.register || mod).service('LoginService', LoginService);

});
