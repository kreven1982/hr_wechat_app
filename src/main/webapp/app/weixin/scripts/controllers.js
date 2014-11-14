"use strict";

var weixinApp = angular.module('weixinApp');

weixinApp.controller('jobListController', [
    '$scope', '$rootScope', '$http', '$location', '$routeParams', 'constantsService',
    function($scope, $rootScope, $http, $location, $routeParams, constantsService) {

    $scope.data = {};
    $scope.search = $location.search();
    $rootScope.title = "";

    $scope.hasSearchCriteria = Object.keys( $scope.search ).length != 0;

    var NO_JOB_RESULT = "没有符合的职位信息,请重新搜索";

    var LOADING = "正在搜索中,请耐心等待...";
    $scope.message = LOADING;

    constantsService.getDiplomas().then(function(response) {
        var diplomas = response.data.diplomas;
        diplomas.shift(); //remove the first item which is "none"
        $scope.data.diploma = diplomas;
    });

    var url =  'api/job/search?';

    angular.forEach($scope.search,function(value,index){
        url += index + "=" + value + "&";
    });

    $http.get(url).success(function(data, status, headers, config){
       $scope.jobs = data.jobs;
       $scope.total = data.total;
       if(data.total == 0) {
            $scope.message = NO_JOB_RESULT;
       }
    });

    $scope.searchJob = function() {
        var toSearch = angular.copy($scope.search);

        if(!toSearch.keyword) {
            delete toSearch.keyword;
        }

        $scope.searchOpen = false;
        $location.search(toSearch);
    };

    $scope.clearSearch = function() {
        $scope.search = {}
    };

    function isEmpty(obj) {
        return Object.keys(obj).length == 0;
    }
}]);

weixinApp.controller('jobController', [
    '$scope', '$http', '$routeParams','$rootScope',
    function($scope, $http, $routeParams, $rootScope) {

    //load job
    var jobId = $routeParams.jobId;

    if(jobId != undefined && jobId != 0) {
        $http.get('api/job/' + jobId).success(function(data, status, headers, config){
            $scope.job = data.result;
            $rootScope.title = " - " + $scope.job.title;
        });
    }
}]);

weixinApp.controller('profileController', [
    '$scope', '$http', '$routeParams', '$location', '$window', 'profileConstant','constantsService', 'multiFormService',
    function($scope, $http, $routeParams, $location, $window, profileConstant, constantsService, multiFormService) {

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

    constantsService.getDiplomas().then(function(response) {
        var diplomas = response.data.diplomas;
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
        $window.history.back();
    };

}]);
