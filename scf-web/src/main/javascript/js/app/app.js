define([
    'require',
    'angular',
    'angular-router',
    'angular-http-auth',
    './constants',
    './controller/main-ctrl',
    './directive/part-refresh-directive'
],function(r,ng){
    var app = ng.module('app',['ngRoute','http-auth-interceptor','app.constants','app.controllers','app.directives']);

    return app;
});
