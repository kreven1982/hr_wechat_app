"use strict";

var weixinApp = angular.module('weixinApp', ['common', 'ngRoute']);

weixinApp.config(function($routeProvider) {
    $routeProvider.when('/jobs', {
            templateUrl : 'app/weixin/views/job.list.html',
            controller  : 'jobListController',
            reloadOnSearch : false
        }).when('/job/:jobId', {
            templateUrl : 'app/weixin/views/job.detail.html',
            controller  : 'jobController'
        }).when('/resume/:jobId', {
            templateUrl : 'app/weixin/views/resume.new.html',
            controller  : 'weixinResumeController'
        }).otherwise({redirectTo: '/jobs'});;
});

