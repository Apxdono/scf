define([
    'require',
    'angular',
    '../app'
],function(r,ng,app){

    function httpRequestInterceptor($log,$injector,$appInfo) {
        var shouldModifyUrl = ['',"",'/',"/"].indexOf($appInfo.contextPath) > -1 ? false : true;
        var intercept = {
            request: function (config) {
                config.requestTimestamp = new Date().getTime();
                if(shouldModifyUrl && config.url.charAt(0) == '/'){
                    config.url = $appInfo.contextPath + config.url;
                }
                return config;
            }
        }
        return intercept;
    }
    httpRequestInterceptor.$inject=['$log','$injector','$appInfo'];
    app.factory('httpRequestInterceptor',httpRequestInterceptor);

});
