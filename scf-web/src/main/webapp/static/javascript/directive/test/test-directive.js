var _t = require('tools');

module.exports = /*@ngInject*/ TestDirective;

function TestDirective($log,UserService){
    return {
        restrict : 'A',
        replace : true,
        scope : true,
        template : '<div style="color : red; font-weight: bold"></div>',
        link : function(scope,element,attributes){
            UserService.subscribe('user',scope,function(n){
                $log.info('VALUES',n);
                element.text(n ? n.username : '');
            });
        }
    };
}
