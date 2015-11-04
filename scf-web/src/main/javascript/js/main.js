require.config({
    baseUrl: location.pathname+'js',
    waitSeconds: 0,
    paths: {
        'angular': 'vendor/angular/angular',
        'angular-router': 'vendor/angular-route/angular-route'
    },

    shim: {
        'angular': {
            exports: 'angular'
        },
        'angular-router': ['angular']
    },

    deps : ['app/app']
});
