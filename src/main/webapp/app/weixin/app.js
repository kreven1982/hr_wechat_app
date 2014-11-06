"use strict";

var weixinApp = angular.module('weixinApp', ['common', 'ngRoute', 'ngAnimate']);

weixinApp.config(function($routeProvider) {
    $routeProvider.when('/jobs', {
            templateUrl : 'app/weixin/views/job.list.html',
            controller  : 'jobListController'
        }).when('/job/:jobId', {
            templateUrl : 'app/weixin/views/job.detail.html',
            controller  : 'jobController'
        }).when('/resume/:jobId', {
            templateUrl : 'app/weixin/views/resume.new.html',
            controller  : 'resumeController'
        }).otherwise({redirectTo: '/jobs'});
});

