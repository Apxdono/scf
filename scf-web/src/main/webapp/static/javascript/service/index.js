var constants = require('../constants.js'),
    angular = require('../angular-shim.js');
var services = angular.module(constants.app.services,[]);
module.exports = services;

services.service(constants.service.user,require('./user-service.js'));

