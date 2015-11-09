define([
    'require',
    'angular',
    './controller-module'
], function (r, ng, mod) {
    function MainCtrl($scope, $log, $http,$rootScope, appEvents) {
        $log.info('started app');
        $scope.welcome = 'Здравствуйте';
        $scope.test = function(){
            $http.get('/test').then(function(){
                $rootScope.$broadcast(appEvents.UPDATE_PARTIALS);
            })
        }
    }

    MainCtrl.$inject = ['$scope', '$log','$http','$rootScope','APP_EVENT'];
    var m = mod.register || mod;
    m.controller('MainCtrl', MainCtrl);
});
