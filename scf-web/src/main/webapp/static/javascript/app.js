var $ = require('jquery');
window.jQuery = $;
require('semantic');
var angular = require('./angular-shim.js');
var constants = require('./constants.js');
var controllers = require('./controller/index.js');
var services = require('./service/index.js');
var appName = constants.app.name;

var app = angular.module(appName,['ngRoute','http-auth-interceptor',constants.app.services, constants.app.controllers]);
/*@ngInject*/
app.config(function($httpProvider) {
    $httpProvider.interceptors.push(constants.service.urlModifyInterceptor);
});

angular.element(document).ready(function() {
    angular.bootstrap(document, [appName]);
    console.log(appName,app);
});

$('.ui.sticky').sticky({
        context: '#content'
    });

//jQuery(function($) {
//    $(window).on("scroll", function () {
//        if ($(this).scrollTop() > 59) {
//            $("#header").addClass("stickyHeader");
//        }
//        else {
//            $("#header").removeClass("stickyHeader");
//        }
//    });
//});

