var _t = require('../tools.js');
var controllers = _t.makeModule('controllers',[]);
module.exports = controllers;

_t.registerUnit(controllers,'controller','main',require('./main-controller.js'));
