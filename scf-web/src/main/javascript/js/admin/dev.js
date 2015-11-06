(function(window,$,ng){
    var ctx = window.location.pathname;
    window.devTools = {};
    devTools.reloadWebContext = function(type){
        $.get(ctx+'/ctx/reload',{
            type:type
        }).then();
    }

    devTools.logout = function(){
        $.get(ctx+'/logout').then();
    }
})(window,jQuery);