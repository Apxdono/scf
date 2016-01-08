var appName = 'leApp';
var constants = {
    app : {
        name : appName,
        controllers : appName+".controllers",
        services : appName+".services",
        directives : appName+".directives"
    },
    controller : {
        main : 'MainController'
    },
    service : {
        user : 'UserService',
        notificator : 'NotificatorService',
        urlModifyInterceptor : 'UrlModifyInterceptorFactory'
    },
    event : {
        scope : {
            destroy : '$destroy'
        }
    }
};
module.exports = constants;
