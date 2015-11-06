define([
    'require',
    'angular',
    'angular-router',
    './controller/main-ctrl',
    './directive/part-refresh-directive'
],function(r,ng){
    var app = ng.module('app',['ngRoute','app.controllers','app.directives']);
    app.controller('MC',function($scope,$rootScope,$http,$log,$timeout){
        $http({
            method : 'GET',
            url: location.pathname+'test'
        }).then(function(response){
            $log.info('got text',response.data);
            $timeout(function(){
                $rootScope.$broadcast('updatePartial');
            },2000);

        })
    });
    function bs(){
        ng.bootstrap($(document).find('html')[0],['app']);
    }
    $(document).ready(bs);

    return app;
});
