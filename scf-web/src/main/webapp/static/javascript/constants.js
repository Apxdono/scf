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
        },
        login : {
            required : 'event:auth-loginRequired',
            confirmed : 'event:auth-loginConfirmed'
        }
    }
};
module.exports = constants;
