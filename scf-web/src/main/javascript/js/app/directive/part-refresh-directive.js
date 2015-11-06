define([
    'require',
    'angular',
    './directive-module'
], function (r, ng, mod) {

    function doRefresh($http,element,params){
        $http({
            method : 'GET',
            url : location.pathname+'partials',
            params : params
        }).then(function(response){
            element.empty().append(response.data);
        },function(reject){
        })
    }

    function PartRefresh($log,$http) {
        return {
            restrict : 'A',
            scope:{
                partRefresh: '=',
                parts : '='
            },
            link : function(scope,element,attributes){
                $log.info('will refresh content at selector',scope.partRefresh,'with parts',scope.parts);
                var listener = scope.$on('updatePartial',function(){
                    $log.info('updating-partials');
                    doRefresh($http,element,{ template : scope.partRefresh, partName : scope.parts});
                });

                var destructor = scope.$on('$destroy',function(){
                    listener();
                    destructor();
                })

            }
        }
    }

    PartRefresh.$inject = ['$log','$http'];
    (mod.register || mod).directive('partRefresh', PartRefresh);
});
