define([
    'require',
    'angular',
    './directive-module'
], function (r, ng, mod) {
    function PartRefresh($log,$http) {
        return {
            restrict : 'A',
            scope:{
                partRefresh: '='
            },
            link : function(scope,element,attributes){
                $log.info('will refresh content at selector ',scope.partRefresh);
            }
        }
    }

    PartRefresh.$inject = ['$log','$http'];
    var m = mod.register || mod;
    (m.register || m).directive('partRefresh', PartRefresh);
});
