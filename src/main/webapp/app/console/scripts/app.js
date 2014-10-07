var consoleApp = angular.module('consoleApp', ['common', 'ngRoute','ui.bootstrap','ui.bootstrap-slider','simditor']);

consoleApp.config(function($routeProvider) {
    $routeProvider.when('/jobs/:page', {
            templateUrl : 'app/console/views/job.list.html',
            controller  : 'jobListController'
        }).when('/job/:jobId', {
            templateUrl : 'app/console/views/job.edit.html',
            controller  : 'jobController'
        }).when('/resumes', {
            templateUrl : 'app/console/views/resume.list.html',
            controller  : 'resumeListController'
        }).otherwise({redirectTo: '/jobs/1'});;
});

