var constants = require('../constants.js'),
    angular = require('../angular-shim.js');
var controllers = angular.module(constants.app.controllers,[]);
module.exports = controllers;

controllers.controller(constants.controller.main,require('./main-controller.js'));

