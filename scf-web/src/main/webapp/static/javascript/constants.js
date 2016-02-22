var appName = 'leApp';
var constants = {
    app : {
        name : appName,
        controllers : appName+".controllers",
        services : appName+".services",
        directives : appName+".directives"
    },
    controllers : {
        main : 'MainController'
    },
    services : {
        user : 'UserService',
        notificator : 'NotificatorService',
        urlModifyInterceptor : 'UrlModifyInterceptorFactory'
    },
    directives : {
        testDir : 'testDirective',
        loginBox : 'loginBox'
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

if(Object.hasOwnProperty('freeze')){
    var freeze = Object.freeze;
    constants = freeze(constants);
}

module.exports =  constants;
