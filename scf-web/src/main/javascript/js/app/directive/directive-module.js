define([
    'require',
    'angular',
],function(r,ng){
    var mod = ng.module('app.directives',[]);
    function $config($compileProvider){
        mod.register = {
            directive: $compileProvider.directive
        };
    }
    $config.$inject = ["$compileProvider"];
    mod.config($config);
    return mod;
});
