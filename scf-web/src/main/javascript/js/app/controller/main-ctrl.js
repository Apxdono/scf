define([
    'require',
    'angular',
    './controller-module'
], function (r, ng, mod) {
    function MainCtrl($scope, $log) {
        $log.info('started app');
        $scope.welcome = 'Здравствуйте';
    }

    MainCtrl.$inject = ['$scope', '$log'];
    var m = mod.register || mod;
    m.controller('MainCtrl', MainCtrl);
});
