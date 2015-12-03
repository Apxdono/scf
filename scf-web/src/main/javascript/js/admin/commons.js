(function(window){
    var commons = {};

    commons.transformRequest = function(obj){
        var str = [];
        for(var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    };

    window.commons = commons;
})(window)