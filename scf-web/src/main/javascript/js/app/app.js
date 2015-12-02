define([
    'require',
    'angular',
    'angular-router',
    './constants',
    './controller/main-ctrl',
    './directive/part-refresh-directive'
],function(r,ng){
    var app = ng.module('app',['ngRoute','app.constants','app.controllers','app.directives']);

    return app;
});
