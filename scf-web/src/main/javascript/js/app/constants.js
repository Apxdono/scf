define([
    'require',
    'angular'
],function(r,ng){
    var constants = ng.module('app.constants',[]);
    constants.constant("APP_EVENT", {
        UPDATE_PARTIALS: "app:updatePartials",
        SCOPE_DESTROY : '$destroy',
        LOGIN_REQUIRED : 'event:auth-loginRequired',
        LOGIN_SUCCESS : 'event:auth-loginConfirmed',
        LOGIN_ERROR : 'app:login-error',
        FORBIDDEN_RESOURCE : 'app:resource-forbidden',
    });
    constants.constant("HTML_EVENT",{
        CLICK : 'click',
        TAP : 'tap',
        MOUSEOVER : 'mouseover'
    });
    constants.constant("$appInfo",ng.extend({},window.appInfo));
    return constants;
});
