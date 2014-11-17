"use strict";

var weixinApp = angular.module('weixinApp', ['common', 'ngRoute', 'ngAnimate', 'ui.bootstrap']);

weixinApp.config(function($routeProvider) {
    $routeProvider.when('/jobs', {
            templateUrl : 'app/weixin/views/job.list.html',
            controller  : 'jobListController',
            reloadOnSearch : false
        }).when('/job/:jobId', {
            templateUrl : 'app/weixin/views/job.detail.html',
            controller  : 'jobController'
        }).when('/profile/:jobId', {
            templateUrl : 'app/weixin/views/profile.new.html',
            controller  : 'profileController'
        }).otherwise({redirectTo: '/jobs'});
});

