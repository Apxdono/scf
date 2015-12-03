define([
    'require',
    'angular',
    './service-module'
], function (r, ng, mod) {

    function LoginService($log, $rootScope, $http, appevent, authService) {
        var s = {};
        function flush(){
            s.user = {};
            s.isLoggedIn = false;
        }

        function fetchProfile() {
            $http({
                method: 'GET',
                url: '/profile'
            }).then(function (res) {
                    $log.info('login-success', res);
                    s.user = res.data;
                    s.isLoggedIn = true;
                    authService.loginConfirmed(s.user);
                }
            )
        };

        function fetchAfterLogin(){
            fetchProfile(true);
        }

        s.login = function (data) {
            $http({
                method: 'POST',
                url: '/login',
                data: data,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: commons.transformRequest
            }).then(ng.noop, function (reject) {
                $log.info('login-error', reject);
            })
        };

        $rootScope.$on(appevent.LOGIN_REQUIRED,flush);
        $rootScope.$on(appevent.LOGIN_ERROR,flush);
        fetchProfile();
        return s;
    }

    LoginService.$inject = ['$log', '$rootScope', '$http', 'APP_EVENT', 'authService'];
    (mod.register || mod).service('LoginService',LoginService);

});
