define([
    'require',
    'angular',
    'angular-router',
    './constants',
    './service/service-module',
    './controller/main-ctrl',
    './directive/part-refresh-directive'
],function(r,ng){
    var app = ng.module('app',['ngRoute','app.constants','app.services','app.controllers','app.directives']);

    return app;
});
