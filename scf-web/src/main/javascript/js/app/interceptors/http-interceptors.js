define([
    'require',
    'angular',
    '../app'
],function(r,ng,app){
    'use strict';

    function PendingRequests($injector){
        var buffer = [],$http;
        function retry(request,extraConfig){
            $http = $http || $injector.get('$http');
            if(extraConfig){
                ng.extend(config,extraConfig);
            }
            $http(request.config).then(request.deffered.resolve, request.deffered.reject);
        }
        return {
            postpone: function(config,deffered){
                buffer.push({config : config, deffered : deffered});
            },
            rejectAll : function(reason){
                if(reason) {buffer.forEach(function(r){r.deffered.reject(reason)});}
            },
            retryAll : function(extraConfig){
                buffer.forEach(function(r){retry(r,extraConfig)});
                buffer = [];
            }
        }
    }

    function LoginService(appEvent,pendingRequests){
        var listeners = { };
        listeners[appEvent.LOGIN_REQUIRED] = [];
        listeners[appEvent.LOGIN_SUCCESS] = [];
        listeners[appEvent.LOGIN_ERROR] = [];
        listeners[appEvent.FORBIDDEN_RESOURCE] = [];

        function trigger(event,data){
            listeners[event].forEach(function(l){l.apply(data)});
        }

        var service = {
            on : function(event,callback){
                listeners[event].push(callback);
                return function(){
                    var index = listeners[event].indexOf(callback);
                    listeners[event].splice(index,1);
                }
            },
            trigger : function(event/*,data*/){
                var data = arguments[1] || {};
                trigger(event,data);
            }
        };
        service.on(appEvent.LOGIN_SUCCESS,function(d){pendingRequests.retryAll()});
        service.on(appEvent.LOGIN_ERROR,function(d){pendingRequests.rejectAll(d)});
        return service;
    }

    function httpRequestInterceptor($log,$injector,$rootScope,$q,$appInfo, appEvent,pendingRequests, loginService) {
        var shouldModifyUrl = ['',"",'/',"/"].indexOf($appInfo.contextPath) > -1 ? false : true;
        var interceptor = {
            request: function (config) {
                config.requestTimestamp = new Date().getTime();
                if(shouldModifyUrl && config.url.charAt(0) == '/' && !config.urlProcessed){
                    config.urlProcessed = true;
                    config.url = $appInfo.contextPath + config.url;
                }
                return config;
            },

            responseError: function(rejection) {
                if (!rejection.config.ignoreAuthentication) {
                    switch (rejection.status) {
                        case 401:
                            var deferred = $q.defer();
                            pendingRequests.postpone(rejection.config, deferred);
                            loginService.trigger(appEvent.LOGIN_REQUIRED,rejection);
                            return deferred.promise;
                        case 403:
                            loginService.trigger(appEvent.FORBIDDEN_RESOURCE,rejection);
                            break;
                    }
                }
                return $q.reject(rejection);
            }
        };
        return interceptor;
    }

    PendingRequests.$inject = ['$injector'];
    LoginService.$inject = ['APP_EVENT','pendingRequests'];
    httpRequestInterceptor.$inject=['$log','$injector','$rootScope','$q','$appInfo','APP_EVENT','pendingRequests','loginService'];
    app.factory('pendingRequests',PendingRequests);
    app.factory('loginService',LoginService);
    app.factory('httpRequestInterceptor',httpRequestInterceptor);
});