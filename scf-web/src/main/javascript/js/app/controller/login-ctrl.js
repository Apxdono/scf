define([
    'require',
    'angular',
    './controller-module'
], function (r, ng, mod) {
    function loginController($scope, LoginService) {
        $scope.credentials = {};
        $scope.loginPending = function(){
            return LoginService.pending
        };
        $scope.login = function () {
            LoginService.login($scope.credentials);
            $scope.credentials = {};
        };

        $scope.logout = function () {
            LoginService.logout();
        };
    }

    loginController.$inject = ['$scope', 'LoginService'];
    (mod.register || mod).controller('LoginCtrl', loginController);
});
