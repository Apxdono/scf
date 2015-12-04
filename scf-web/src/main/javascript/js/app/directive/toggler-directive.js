define([
    'require',
    'angular',
    './directive-module'
], function (r, ng, mod) {

    function toggler($log,LoginService) {
        return {
            restrict : 'A',
            scope:{
                toggler : '=',
                toggleReverse : '='
            },
            link : function(scope,element,attributes){
                if(!scope.toggler){
                    scope.$watch(function(){return !LoginService.pending && LoginService.isLoggedIn },function(n,o){
                        if(ng.isUndefined(n)) return;
                        if(!n == o) element.toggleClass('hidden');
                    });
                } else {
                    scope.$watch(function(){return !LoginService.isLoggedIn },function(n,o){
                        if(ng.isUndefined(n)) return;
                        if(!n == o) element.toggleClass('hidden');
                    });
                }
            }
        }
    }

    toggler.$inject = ['$log','LoginService'];
    (mod.register || mod).directive('toggler', toggler);
});
