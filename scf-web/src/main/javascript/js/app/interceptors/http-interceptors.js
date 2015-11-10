define([
    'require',
    'angular',
    '../app'
],function(r,ng,app){
    'use strict';

    function httpRequestInterceptor($appInfo) {
        var shouldModifyUrl = ['',"",'/',"/"].indexOf($appInfo.contextPath) > -1 ? false : true;
        var interceptor = {
            request: function (config) {
                config.requestTimestamp = new Date().getTime();
                if(shouldModifyUrl && config.url.charAt(0) == '/' && !config.urlProcessed){
                    config.urlProcessed = true;
                    config.url = $appInfo.contextPath + config.url;
                }
                return config;
            }
        };
        return interceptor;
    }

    httpRequestInterceptor.$inject=['$appInfo'];
    app.factory('httpRequestInterceptor',httpRequestInterceptor);
});