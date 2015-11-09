define([
    'require',
    'angular',
    './directive-module'
], function (r, ng, mod) {

    function doRefresh(scope,$http,element,params,$compile){
        $http({
            method : 'GET',
            url : '/partials',
            params : params
        }).then(function(response){
            element.empty().append(response.data);
        },function(reject){
        })
    }

    function PartRefresh($log,$http,$compile,appevent) {
        return {
            restrict : 'A',
            scope:{
                partRefresh: '=',
                parts : '='
            },
            link : function(scope,element,attributes){
                $log.info('will refresh content at selector',scope.partRefresh,'with parts',scope.parts);
                var listener = scope.$on(appevent.UPDATE_PARTIALS,function(){
                    $log.info('updating-partials');
                    $http.get('/partials',{ params : { template : scope.partRefresh, partName : scope.parts}}).then(function(response){
                        element.empty().append($compile(response.data)(scope.$parent));

                    });
                });

                var destructor = scope.$on(appevent.SCOPE_DESTROY,function(){
                    listener();
                    destructor();
                })

            }
        }
    }

    PartRefresh.$inject = ['$log','$http','$compile','APP_EVENT'];
    (mod.register || mod).directive('partRefresh', PartRefresh);
});
