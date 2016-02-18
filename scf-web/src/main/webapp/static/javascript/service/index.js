var _t = require('../tools.js');
var services = _t.makeModule('services',[]);
module.exports = services;
_t.registerUnit(services,'service','notificator',require('./notificator-service.js'));
_t.registerUnit(services,'service','user',require('./user-service.js'));
_t.registerUnit(services,'factory','urlModifyInterceptor',require('./url-modify-interceptor.js'));

