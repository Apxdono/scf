define([
    'require',
    'angular',
    './controller-module'
], function (r, ng, mod) {
    function MainCtrl($scope, $log, $http,$rootScope, appEvents, loginService) {
        $log.info('started app');
        $scope.welcome = 'Здравствуйте';
        $scope.test = function(){
            $http.get('/test').then(function(){
                $rootScope.$broadcast(appEvents.UPDATE_PARTIALS);
            });
            var cb = loginService.on(appEvents.LOGIN_REQUIRED,function(){
                loginService.trigger(appEvents.LOGIN_SUCCESS,{data:'someData'});
                cb();
            })
        }
    }

    MainCtrl.$inject = ['$scope', '$log','$http','$rootScope','APP_EVENT','loginService'];
    var m = mod.register || mod;
    m.controller('MainCtrl', MainCtrl);
});
