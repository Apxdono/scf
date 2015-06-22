define([
    'require',
    'angular',
    './controller-module'
],function(r,ng,module){
    'use strict;'

    function MainCtrl($scope,$log){
        $log.info('started app');
        $scope.welcome = 'Здравствуйте';
    }
    MainCtrl.$inject = ["$scope", "$log"];

    module.controller('MainCtrl',MainCtrl);
});
