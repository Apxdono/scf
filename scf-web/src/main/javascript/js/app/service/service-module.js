define([
    'require',
    'angular',
    'angular-http-auth',
    '../constants'
], function (r, ng) {

    var mod = ng.module('app.services', ['app.constants','http-auth-interceptor']);
    function $config($provide) {
        mod.register = {
            service: $provide.service,
            factory: $provide.factory
        };
    }

    $config.$inject = ["$provide"];
    mod.config($config);
    return mod;
});
