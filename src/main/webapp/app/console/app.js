var consoleApp = angular.module('consoleApp', ['common', 'ngRoute','ui.bootstrap','ui.bootstrap-slider','simditor', 'ngAnimate']);

consoleApp.config(function($routeProvider, $httpProvider) {

    var viewPath = "app/console/views/";

    $routeProvider.when('/jobs', {
            templateUrl : viewPath + 'job.list.html',
            controller  : 'jobListController',
            reloadOnSearch : false,
            resolve : {
                userInfo : function(userService) {
                    return userService.getUserInfo();
                }
            }
        }).when('/job/:jobId', {
            templateUrl : viewPath + 'job.edit.html',
            controller  : 'jobController'
        }).when('/profiles', {
            templateUrl : viewPath + 'profile.list.html',
            controller  : 'profileListController'
        }).when('/searchJob', {
            templateUrl : viewPath + 'job.list.html',
            controller  : 'jobSearchController'
        }).otherwise({redirectTo: '/jobs'});


    //Register a global interceptor to detect if any permission denied event, then should force user go back to login page.
    $httpProvider.interceptors.push('authInterceptor');

});

