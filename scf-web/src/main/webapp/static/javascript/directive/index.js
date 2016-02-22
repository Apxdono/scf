var _t = require('tools');
var directives = _t.makeModule('directives',[]);
module.exports = directives;

_t.registerUnit(directives,'directive','testDir',require('./test/test-directive.js'));
_t.registerUnit(directives,'directive','loginBox',require('./login/login-directive.js'));
