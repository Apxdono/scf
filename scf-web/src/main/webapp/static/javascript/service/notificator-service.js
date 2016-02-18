var _t = require('../tools.js');
var utils = require('../utils.js');
module.exports = /*@ngInject*/ NotificatorService;
function NotificatorService($log){
    'use strict';

    var service = {
        _listeners : {},
        subscribe : function(fnName,target,assigner){
            var self = this;
            assigner = assigner || _t.ng.noop;
            if(_t.ng.isUndefined(target) || _t.ng.isUndefined(assigner)) {
                return  _t.ng.noop;
            }
            if(_t.ng.isUndefined(self._listeners[fnName])){
                self._listeners[fnName] = [];
            }
            var cb = _t.ng.isFunction(assigner) ? assigner : function(v){
                target[assigner] = v;
            };
            var listener = {target : target,callback : cb};
            self._listeners[fnName].push(listener);
            var ready = (self[fnName] || _t.ng.noop).call();
            if(!_t.ng.isUndefined(ready)){
                cb(ready);
            }
            var clear = function(){
                $log.info('remove subscription for',target);
                utils.arrays.remove(self._listeners[fnName],listener);
            };

            return target.constructor.name !== 'Scope' ? (clear) : (target.$on(_t.constants.event.scope.destroy,clear),  _t.ng.noop);
        },
        notify : function(fnName,value){
            var self = this, listeners =self._listeners[fnName];
            if(!listeners) {
                return;
            }
            listeners.forEach(function(v){
                v.callback.call(v.target,value);
            });
        }
    };

    var extender = {
        addNature : function(target){
            return _t.ng.extend(target,_t.ng.copy(service,{}));
        },
        notify : service.notify
    };
    return extender;
}
