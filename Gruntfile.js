module.exports = function(grunt) {


    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);

    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Project configuration.
    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        //Install and update npm and bower dependencies.
        //It looks for 'package.json' and 'bower.json' files, and runs 'npm install' and 'bower install' respectively only if they exist.
        auto_install: {
            local: {},
            subdir: {
                options: {
                    cwd: '',
                    stdout: true,
                    stderr: true,
                    failOnError: true
                }
            }
        },

        //Standard uglify plugin
        uglify: {
            options: {},
            my_target: {
                files: {
                    'build/webapp/app/console.js': ['build/webapp/app/console.js'],
                    'build/webapp/app/common.js': ['build/webapp/app/common.js'],
                    'build/webapp/app/weixin.js': ['build/webapp/app/weixin.js'],
                    'build/webapp/app/login.js': ['build/webapp/app/login.js']
                }
            }
        },
        concat: {
            basic_and_extras: {
                files: {
                    'build/webapp/app/console.js': ['build/webapp/app/console/**/*.js'],
                    'build/webapp/app/common.js': ['build/webapp/app/common/**/*.js'],
                    'build/webapp/app/login.js': ['build/webapp/app/login/**/*.js'],
                    'build/webapp/app/weixin.js': ['build/webapp/app/weixin/**/*.js']
                }
            }
        },
        less: {
            development: {
                options: {
                    paths: ["src/main/webapp/less"]
                },
                files: {
                    "build/webapp/css/console.css": "src/main/webapp/less/console.less",
                    "build/webapp/css/login.css": "src/main/webapp/less/login.less",
                    "build/webapp/css/weixin.css": "src/main/webapp/less/weixin.less"
                }
            }
        },
        jshint: {
            options: {
                reporter: require('jshint-stylish'),
                jshintrc: true
            },
            all: ['src/main/webapp/app/**/*.js']
        },
        copy: {
            main: {
                expand: true,
                cwd: 'src/main/webapp/',
                src: '**',
                dest: 'build/webapp/',
                flatten: false,
                filter: 'isFile'
            }
        },
        wiredep: {

          task: {

            // Point to the files that should be updated when
            // you run `grunt wiredep`
            src: [
              'src/main/webapp/login',
              'src/main/webapp/console',
              'src/main/webapp/weixin'
            ],
            exclude: [ "bootstrap.js" ]
          }
        }
    });

    // Default task(s).
    grunt.registerTask('default', ['auto_install', 'jshint', 'copy','concat', 'uglify']);

};