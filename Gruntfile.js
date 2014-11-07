module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        auto_install: {
            local: {},
            subdir: {
                options: {
                    cwd: 'subdir',
                    stdout: true,
                    stderr: true,
                    failOnError: true
                }
            }
        },

        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            build: {
                src: 'src/<%= pkg.name %>.js',
                dest: 'build/<%= pkg.name %>.min.js'
            }
        },
        wiredep: {

          task: {

            // Point to the files that should be updated when
            // you run `grunt wiredep`
            src: [
              'src/main/webapp/console',
              'src/main/webapp/weixin'
            ],
            exclude: [ "bootstrap.js" ]
          }
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-auto-install');
    grunt.loadNpmTasks('grunt-wiredep');


    // Default task(s).
    grunt.registerTask('default', ['uglify']);

};