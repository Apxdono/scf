define([
    'require',
    'angular',
    'angular-router',
    './controller/main-ctrl'
],function(r,ng){
    var app = ng.module('app',['ngRoute','app.controllers']);
    function bs(){
        ng.bootstrap($(document).find('html')[0],['app']);
    }
    $(document).ready(bs);
    return app;
});
