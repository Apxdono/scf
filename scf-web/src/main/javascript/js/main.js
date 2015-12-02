require.config({
    baseUrl: location.pathname+'js',
    waitSeconds: 0,
    paths: {
        'angular': 'vendor/angular/angular',
        'angular-router': 'vendor/angular-route/angular-route',
        'angular-http-auth': 'vendor/angular-http-auth/src/http-auth-interceptor'
    },

    shim: {
        'angular': {
            exports: 'angular'
        },
        'angular-router': ['angular'],
        'angular-http-auth' : ['angular']
    },

    deps : ['app/startup']
});
