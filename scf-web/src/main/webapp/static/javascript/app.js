var _t = require('./tools.js');
var controllers = require('./controller/index.js');
var services = require('./service/index.js');
var directives = require('./directive/index.js');
var appName = _t.constants.app.name;

var app = _t.ng.module(appName,['ngRoute','http-auth-interceptor', _t.constants.app.services, _t.constants.app.controllers, _t.constants.app.directives]);
/*@ngInject*/
app.config(function($httpProvider) {
    $httpProvider.interceptors.push(_t.constants.services.urlModifyInterceptor);
});

_t.ng.element(document).ready(function() {
    _t.ng.bootstrap(document, [appName]);
    console.log(appName,app);
});
