define([
    'require',
    'angular'
],function(r,ng){
    var constants = ng.module('app.constants',[]);
    constants.constant("APP_EVENT", {
        UPDATE_PARTIALS: "updatePartials",
        SCOPE_DESTROY : '$destroy'
    });
    constants.constant("HTML_EVENT",{
        CLICK : 'click',
        TAP : 'tap',
        MOUSEOVER : 'mouseover'
    });
    constants.constant("$appInfo",ng.extend({},window.appInfo));
    return constants;
});
