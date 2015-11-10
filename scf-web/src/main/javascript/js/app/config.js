define([
    'require',
    'angular',
    './app',
    './interceptors/http-interceptors'
],function(r,ng,app){
    app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('httpRequestInterceptor');
    }]);
    return app;
});
