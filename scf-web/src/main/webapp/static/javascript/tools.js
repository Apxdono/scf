var $ = require('jquery');
window.jQuery = $;
require('semantic');
var constants = require('./constants.js'),
    angular = require('./angular-shim.js');
var tools = {};

module.exports = tools;

(function(angular, $, constants){
    "use strict";
    function makeModule(key,deps){
        var module =  angular.module(constants.app[key],deps);
        module._appkey = key;
        return module;
    }

    function registerUnit(module,unitType,key,file){
        var targetConstant = constants;
        module._appkey.split(".").forEach(function(e){ targetConstant = targetConstant[e]; });
        module[unitType].apply(module,[targetConstant[key],file]);
    }

    tools.makeModule = makeModule;
    tools.registerUnit = registerUnit;
    tools.ng = angular;
    tools.constants = constants;
    tools.$ = $;
    tools.appInfo = window.appInfo;
})(angular, jQuery, constants);