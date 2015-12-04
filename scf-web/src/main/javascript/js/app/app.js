define([
    'require',
    'angular',
    'angular-router',
    './constants',
    './service/service-index',
    './directive/directive-index',
    './controller/controller-index'
],function(r,ng){
    var app = ng.module('app',['ngRoute','app.services','app.controllers','app.directives']);

    return app;
});
