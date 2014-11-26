module.exports = function(grunt) {

    var path = require('path');

    //for less to integrate with grunt-usemin
    var lessCreateConfig = function (context, block) {
        var cfg = {files: []};
        var outfile = path.join(context.outDir, block.dest);
        var filesDef = {};

        filesDef.dest = outfile;
        filesDef.src = [];

        context.inFiles.forEach(function (inFile) {
            filesDef.src.push(path.join(context.inDir, inFile));
        });

        cfg.files.push(filesDef);
        context.outFiles = [block.dest];
        return cfg;
    };

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);

    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Project configuration.
    grunt.initConfig({

        appConfig: {
            src: "src/main/webapp",
            dist: "build/grunt_build"
        },

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
        // Empties folders to start fresh, it is VERY DANGEROUS
        clean: {
            dist: {
                files: [{
                    dot: true,
                    src: [
                        '.tmp', '<%= appConfig.dist %>'
                    ]
                }]
            }
        },

        //Standard uglify plugin
//        uglify: {
//            options: {},
//            my_target: {
//                files: {
//                    '<%= appConfig.dist %>/app/console.js': ['<%= appConfig.dist %>/app/console.js'],
//                    '<%= appConfig.dist %>/app/common.js': ['<%= appConfig.dist %>/app/common.js'],
//                    '<%= appConfig.dist %>/app/weixin.js': ['<%= appConfig.dist %>/app/weixin.js'],
//                    '<%= appConfig.dist %>/app/login.js': ['<%= appConfig.dist %>/app/login.js']
//                }
//            }
//        },
//        concat: {
//            basic_and_extras: {
//                files: {
//                    '<%= appConfig.dist %>/app/console.js': ['<%= appConfig.dist %>/app/console/**/*.js'],
//                    '<%= appConfig.dist %>/app/common.js': ['<%= appConfig.dist %>/app/common/**/*.js'],
//                    '<%= appConfig.dist %>/app/login.js': ['<%= appConfig.dist %>/app/login/**/*.js'],
//                    '<%= appConfig.dist %>/app/weixin.js': ['<%= appConfig.dist %>/app/weixin/**/*.js']
//                }
//            }
//        },
//        less: {
//            development: {
//                options: {
//                    paths: ["<%= appConfig.src %>/less"]
//                },
//                files: {
//                    "<%= appConfig.dist %>/css/console.css": "<%= appConfig.src %>/less/console.less",
//                    "<%= appConfig.dist %>/css/login.css": "<%= appConfig.src %>/less/login.less",
//                    "<%= appConfig.dist %>/css/weixin.css": "<%= appConfig.src %>/less/weixin.less"
//                }
//            }
//        },
        jshint: {
            options: {
                reporter: require('jshint-stylish'),
                jshintrc: true
            },
            all: ['<%= appConfig.src %>/app/**/*.js']
        },
        copy: {
            main: {
                expand: true,
                cwd: '<%= appConfig.src %>/',
                src: ['fonts/**', 'images/**', 'app/**/*.html', 'console', 'weixin', 'login'],
                dest: '<%= appConfig.dist %>/',
                flatten: false,
                filter: 'isFile'
            }
        },
        wiredep: {

          task: {
            // Point to the files that should be updated when you run `grunt wiredep`
            src: [
              '<%= appConfig.src %>/login',
              '<%= appConfig.src %>/console',
              '<%= appConfig.src %>/weixin'
            ],
            exclude: [ "bootstrap.js" ]
          }
        },
        // ng-annotate tries to make the code safe for minification automatically
        // by using the Angular long form for dependency injection.
        ngAnnotate: {
            dist: {
                files: [{
                    expand: true,
                    cwd: '.tmp/concat/scripts',
                    src: ['*.js', '!vendor*.js'],
                    dest: '.tmp/concat/scripts'
                }]
            }
        },
        filerev: {
            dist: {
                options: {
                    algorithm: 'md5',
                    length: 8
                },
                src: [
                    '<%= appConfig.dist %>/scripts/{,*/}*.js',
                    '<%= appConfig.dist %>/styles/{,*/}*.css',
                    '<%= appConfig.dist %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
                    '<%= appConfig.dist %>/fonts/*'
                ]
            }
        },
        // Reads HTML for usemin blocks to enable smart builds that automatically
        // concat, minify and revision files. Creates configurations in memory so
        // additional tasks can operate on them
        useminPrepare: {
            html: ['<%= appConfig.src %>/console', '<%= appConfig.src %>/weixin', '<%= appConfig.src %>/login'],
            options: {
                root: "<%= appConfig.src %>",
                dest: '<%= appConfig.dist %>',
                flow: {
                    html: {
                        steps: {
                            js: ['concat', 'uglifyjs'],
                            css: ['concat','cssmin'],
                            'less': [{
                                name: 'less',
                                createConfig: lessCreateConfig
                            }, "cssmin"]
                        },
                        post: {}
                    }
                }
            }
        },
        // Performs rewrites based on filerev and the useminPrepare configuration
        usemin: {
            html: [
                    '<%= appConfig.dist %>/console',
                    '<%= appConfig.dist %>/weixin',
                    '<%= appConfig.dist %>/login'
            ],
            css: ['<%= appConfig.dist %>/styles/{,*/}*.css'],
            options: {
                assetsDirs: ['<%= appConfig.dist %>','<%= appConfig.dist %>/images'],
                blockReplacements: {
                    less: function (block) {
                        return '<link rel="stylesheet" href="' + block.dest + '" />';
                    }
                }
            }
        }
    });

    // Default task(s).
    grunt.registerTask('buildWebApp',
        [
            'auto_install',
            'jshint',
            'clean:dist',
            'useminPrepare',
            'copy',
            'less',
            'concat',
            'ngAnnotate',
            'cssmin',
            'uglify',
            'filerev',
            'usemin'
        ]
    );

};