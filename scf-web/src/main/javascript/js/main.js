try{
    (function (window) {
        window.require.config({
            baseUrl: '/static',
            waitSeconds: 0,
            paths: {
                'angular' : 'vendor/angular/angular.min'
            },

            shim: {
                'angular': {
                    exports: 'angular'
                }
            },

            deps: ['js/app/app']
        });
    })(window);
} finally {

}