module.exports = function(grunt) {
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-browserify');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-ng-annotate');

    grunt.registerTask('default', ['clean','jshint','browserify', 'watch']);
    grunt.registerTask('prod', ['clean','jshint','browserify', 'uglify']);

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        dir: {
            dest: 'src/main/webapp/static/js',
            src: 'src/main/webapp/static/javascript'
        },
        bundle: {
            src : 'app.js',
            dev: 'bundle.js',
            release: 'bundle.min.js'
        },
        clean : {
            main : ['<%= dir.dest %>/*.js']
        },
        jshint: {
            options: {
                curly: true,
                eqeqeq: true,
                eqnull: true,
                browser: true,
                globals: {
                    jQuery: true
                },
            },
            src : ['<%= dir.src %>/**/*.js']
        },
        browserify: {
            main: {
                src: '<%= dir.src %>/<%= bundle.src %>',
                dest: '<%= dir.dest %>/<%= bundle.dev %>',
                options: {
                    transform: ['debowerify','browserify-ngannotate'],
                    browserifyOptions: {
                        debug: true
                    }
                }
            }
        },
        ngAnnotate: {
            options: {
                add : true,
                remove : false,
                singleQuotes: true
            },
            main: {
                files : {
                    src : ['<%= dir.src %>/**/*.js']
                }
            },
        },
        uglify: {
            main:{
                options: {
                    wrap: true,
                    mangle:true
                },
                files: {
                    '<%= dir.dest %>/<%= bundle.release %>' : ['<%= dir.dest %>/<%= bundle.dev %>']
                }
            }
        },
        watch: {
            files: '<%= dir.src %>/**/*.js',
            tasks: ['default']
        }
    });
}