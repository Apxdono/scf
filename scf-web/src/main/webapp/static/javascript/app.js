var $ = require('jquery');
var angular = require('./angular-shim.js');
var constants = require('./constants.js');
var controllers = require('./controller/index.js');
var controllers = require('./service/index.js');
var appName = constants.app.name;

var app = angular.module(appName,[constants.app.services, constants.app.controllers]);

angular.element(document).ready(function() {
    angular.bootstrap(document, [appName]);
    console.log(appName,app);
});


