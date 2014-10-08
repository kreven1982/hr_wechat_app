var consoleApp = angular.module('consoleApp', ['common', 'ngRoute','ui.bootstrap','ui.bootstrap-slider','simditor']);

consoleApp.config(function($routeProvider) {
    $routeProvider.when('/jobs', {
            templateUrl : 'app/console/views/job.list.html',
            controller  : 'jobListController',
            reloadOnSearch : false
        }).when('/job/:jobId', {
            templateUrl : 'app/console/views/job.edit.html',
            controller  : 'jobController'
        }).when('/resumes', {
            templateUrl : 'app/console/views/resume.list.html',
            controller  : 'resumeListController'
        }).when('/searchJob', {
            templateUrl : 'app/console/views/job.list.html',
            controller  : 'jobSearchController'
        }).otherwise({redirectTo: '/jobs'});;
});

