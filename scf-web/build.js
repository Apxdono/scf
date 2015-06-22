({
    baseUrl : 'src/main/javascript',
    out : 'dist/main.js',
    include : ['js/main','js/app/app'],
    wrap : true,
    //mainConfigFile : 'src/main/javascript/js/main.js',
    findNestedDependencies: true,
    //modules: [
    //    {
    //        name: 'js/main',
    //        include : ['../src/main/javascript/js']
    //    }
    //],
    optimize : 'uglify2',
    generateSourceMaps : true,
    preserveLicenseComments : false,
    //fileExclusionRegExp: /^(r|require|build)\.js$/,
    //optimizeCss: 'standard',
    //removeCombined: true,
    paths: {
        'angular' : 'vendor/angular/angular.min'
    }

})