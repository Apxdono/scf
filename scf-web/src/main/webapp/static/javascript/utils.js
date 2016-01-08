var utils = {
    transformRequest : function (obj) {
        var str = [];
        for (var p in obj) {
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        }
        return str.join("&");
    },
    arrays : {
        remove : function(target,element){
            target.slice(target.indexOf(element),1);
        }
    }
};
module.exports = utils;
