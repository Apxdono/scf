({
    mainConfigFile:'src/main/javascript/js/main.js',
    baseUrl : 'src/main/javascript/js',
    out : 'dist/main.js',
    include : ['main','app/app'],
    wrap : true,
    findNestedDependencies: true,
    generateSourceMaps : false,
    preserveLicenseComments : false,
    optimize : 'uglify2',
    uglify2 : {
        output: {
            beautify : false,
            mangle : true
        },
        beautify: {
            semicolons: false
        }
    },
    paths: {
        angular : 'empty:',
        'angular-router' : 'empty:'
    }

})