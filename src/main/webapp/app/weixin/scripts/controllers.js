"use strict";

var weixinApp = angular.module('weixinApp');

weixinApp.controller('jobListController', [
    '$log', '$scope', '$rootScope', '$http', '$location', '$routeParams', 'constantsService', "utils",
    function($log, $scope, $rootScope, $http, $location, $routeParams, constantsService, utils) {

    $log.debug("in weixin App jobListController");

    var NO_JOB_RESULT = "没有符合的职位信息,请重新搜索";
    var LOADING = "正在搜索中,请耐心等待...";

    $scope.total = 99; //given pagination control a chance to allow actual page selected
    $scope.pageSize = 1;

    $scope.data = {};
    $rootScope.title = "";

    $scope.search = $location.search();

    if(!$scope.search.page) {
        $scope.search.page = 1; //initialize page if not exist
    }

    //Initialize locations
    constantsService.getOfficeLocations().then(function(data) {
        $scope.data.locations = data.locations;
    });

    constantsService.getDiplomas().then(function(data) {
        var diplomas = data.diplomas;
        diplomas.shift(); //remove the first item which is "none"
        $scope.data.diploma = diplomas;
    });

    $scope.$watch("search.page", function(){
        $scope.searchJob(false);
    },true);

    $scope.searchJob = function(reset) {
        $scope.searchOpen = false;
        $scope.message = LOADING;

        var toSearch = utils.purifyObject($scope.search);

        if(reset) {
            toSearch.page = 1;
        }

        $scope.hasSearchCriteria = utils.checkSize(toSearch) > 1;

        var url =  'api/job/search?activated=true&';

        angular.forEach(toSearch,function(value,index){
            if(value) {
                url += index + "=" + value + "&";
            }
        });

        $http.get(url).success(function(data, status, headers, config){
            $scope.jobs = data.jobs;
            $scope.total = data.total;
            $scope.pageSize = data.pageSize;
            $scope.pageTotal = data.total % data.pageSize ?  (data.total / data.pageSize + 1) : (data.total / data.pageSize);

            $location.search(toSearch);

            if(data.total == 0) {
                $scope.message = NO_JOB_RESULT;
            }
        });
    };

    $scope.clearSearch = function() {
        $scope.search = {
            page : 1
        }
    };
}]);

weixinApp.controller('jobController', [
    '$scope', '$http', '$routeParams','utils',
    function($scope, $http, $routeParams, utils) {

    //load job
    var jobId = $routeParams.jobId;

    if(jobId != undefined && jobId != 0) {
        $http.get('api/job/' + jobId + "?updateViewCount=true").success(function(data, status, headers, config){
            $scope.job = data.result;
            utils.setTitle(" - " + $scope.job.title);
        });
    }
}]);

weixinApp.controller('profileController', [
    '$scope', '$http', '$routeParams', '$location', 'utils', 'profileConstant','constantsService', 'multiFormService',
    function($scope, $http, $routeParams, $location, utils, profileConstant, constantsService, multiFormService) {

    $scope.validated = false;

    $scope.profile = {
        name: "",
        mobile: "",
        experience: "",
        diploma : '',
        detail: ""
    };

    $scope.data = {
        experiences: profileConstant.experiences,
        diploma : []
    };

    constantsService.getDiplomas().then(function(data) {
        var diplomas = data.diplomas;
        diplomas.shift(); //remove the first item which is "none"
        $scope.data.diplomas = diplomas;
    });

    $scope.submitProfile = function(){
    	$scope.validated = true;
        
    	if($scope.profileForm.$valid) {
            var jobId = $routeParams.jobId;
            multiFormService.submitMultiFormWithFile($scope.profileAttachment, "api/profile/" + $routeParams.jobId, JSON.stringify($scope.profile), function(data, status, headers, config){
                $location.path("#/job/" + jobId);
            });
    	}
    };

    $scope.goBack = function() {
        utils.goBack();
    };
}]);
