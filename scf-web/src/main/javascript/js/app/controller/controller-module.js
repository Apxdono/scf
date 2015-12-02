define([
    'require',
    'angular',
    'angular-http-auth',
],function(r,ng){
    var mod = ng.module('app.controllers',['http-auth-interceptor']);
    function $config($controllerProvider,$compileProvider, $filterProvider, $provide){
        mod.register = {
            controller: $controllerProvider.register,
            directive: $compileProvider.directive,
            filter: $filterProvider.register,
            factory: $provide.factory,
            service: $provide.service
        };
    }
    $config.$inject = ["$controllerProvider", "$compileProvider", "$filterProvider", "$provide"];
    mod.config($config);
    return mod;
});
