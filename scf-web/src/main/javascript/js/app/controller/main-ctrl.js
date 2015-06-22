define([
    'require',
    'angular',
    './controller-module'
],function(r,ng,module){
    function MainCtrl($scope,$log){
        $log.info('started app');
        $scope.welcome = 'Здравствуйте';
    }

    module.controller('MainCtrl',["$scope", "$log",MainCtrl]);
});
