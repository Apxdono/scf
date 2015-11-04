define([
    'require',
    'angular',
    'angular-router',
    './controller/main-ctrl',
    './directive/part-refresh-directive'
],function(r,ng){
    var app = ng.module('app',['ngRoute','app.controllers','app.directives']);
    function bs(){
        ng.bootstrap($(document).find('html')[0],['app']);
    }
    $(document).ready(bs);
    return app;
});
