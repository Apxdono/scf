var angular = require('../angular-shim.js');
var constants = require('../constants.js');
var utils = require('../utils.js');
module.exports = /*@ngInject*/ NotificatorService;
function NotificatorService($log){
    'use strict';

    var service = {
        _listeners : {},
        subscribe : function(fnName,target,assigner){
            var self = this;
            assigner = assigner || angular.noop;
            if(angular.isUndefined(target) || angular.isUndefined(assigner)) {
                return  angular.noop;
            }
            if(angular.isUndefined(self._listeners[fnName])){
                self._listeners[fnName] = [];
            }
            var cb = angular.isFunction(assigner) ? assigner : function(v){
                target[assigner] = v;
            };
            var listener = {target : target,callback : cb};
            self._listeners[fnName].push(listener);
            var ready = (self[fnName] || angular.noop).call();
            if(!angular.isUndefined(ready)){
                cb(ready);
            }
            var clear = function(){
                $log.info('remove subscription for',target);
                utils.arrays.remove(self._listeners[fnName],listener);
            };

            return target.constructor.name !== 'Scope' ? (clear) : (target.$on(constants.event.scope.destroy,clear),  angular.noop);
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
            return angular.extend(target,angular.copy(service,{}));
        },
        notify : service.notify
    };
    return extender;
}
