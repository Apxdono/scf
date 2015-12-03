define([
    'require',
    'angular',
    './controller-module'
], function (r, ng, mod) {
    function loginController($scope, LoginService) {
        $scope.credentials = {};
        $scope.login = function () {
            LoginService.login($scope.credentials);
        }
    }

    loginController.$inject = ['$scope', 'LoginService'];
    (mod.register || mod).controller('LoginCtrl', loginController);
});
