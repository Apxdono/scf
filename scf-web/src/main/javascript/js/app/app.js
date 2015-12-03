define([
    'require',
    'angular',
    'angular-router',
    './constants',
    './service/login-service',
    './controller/main-ctrl',
    './controller/login-ctrl',
    './directive/part-refresh-directive'
],function(r,ng){
    var app = ng.module('app',['ngRoute','app.services','app.controllers','app.directives']);

    return app;
});
