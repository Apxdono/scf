var _t = require('../tools.js');
module.exports = /*@ngInject*/ httpRequestInterceptor;

function httpRequestInterceptor() {
    var shouldModifyUrl = ['',"",'/',"/"].indexOf(window.appInfo.contextPath) > -1 ? false : true;
    var interceptor = {
        request: function (config) {
            config.requestTimestamp = new Date().getTime();
            if(shouldModifyUrl && config.url.charAt(0) === '/' && !config.urlProcessed){
                config.urlProcessed = true;
                config.url = _t.appInfo.contextPath + config.url;
            }
            return config;
        }
    };
    return interceptor;
}
