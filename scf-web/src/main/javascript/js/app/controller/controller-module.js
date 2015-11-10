define([
    'require',
    'angular'
],function(r,ng){
    var mod = ng.module('app.controllers',[]);
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
