var consoleApp = angular.module('consoleApp', ['common', 'ngRoute','ui.bootstrap','ui.bootstrap-slider','simditor']);

consoleApp.config(function($routeProvider, $httpProvider) {

    var viewPath = "app/console/views/";

    $routeProvider.when('/jobs', {
            templateUrl : viewPath + 'job.list.html',
            controller  : 'jobListController',
            reloadOnSearch : false
        }).when('/job/:jobId', {
            templateUrl : viewPath + 'job.edit.html',
            controller  : 'jobController'
        }).when('/resumes', {
            templateUrl : viewPath + 'resume.list.html',
            controller  : 'resumeListController'
        }).when('/searchJob', {
            templateUrl : viewPath + 'job.list.html',
            controller  : 'jobSearchController'
        }).otherwise({redirectTo: '/jobs'});


    $httpProvider.interceptors.push('authInterceptor');

});
