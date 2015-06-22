define([
    'require',
    'angular',
    './controller/controller-module',
    './controller/main-ctrl'
],function(r,ng){
    'use strict;'

    var app = ng.module('app',['app.controllers']);
    (function bootstrap(){
        ng.bootstrap(document,['app']);
    })();
    return app;
});
