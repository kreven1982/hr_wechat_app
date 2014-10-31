"use strict";

var weixinApp = angular.module('weixinApp');

weixinApp.controller('jobListController', ['$scope', '$http', '$location', '$routeParams', 'constantsService', function($scope, $http, $location, $routeParams, constantsService) {

    $scope.data = {};
    $scope.search = $location.search();

    $scope.hasSearchCriteria = Object.keys( $scope.search ).length != 0;

    console.log( Object.keys({ key : "value"}).length);
    console.log($scope.hasSearchCriteria);
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
       $scope.jobs = data.result.jobs;
       $scope.total = data.result.total;
       if(data.result.total == 0) {
            $scope.message = NO_JOB_RESULT;
       }
    });

    $scope.searchJob = function() {
        var toSearch = angular.copy($scope.search);
        $scope.searchOpen = false;
        $location.search(toSearch);
    };

    function isEmpty(obj) {
        return Object.keys(obj).length == 0;
    }
}]);

weixinApp.controller('jobController', ['$scope', '$http', '$routeParams','$rootScope', function($scope, $http, $routeParams, $rootScope) {

    //load job
    var jobId = $routeParams.jobId;

    if(jobId != undefined && jobId != 0) {
        $http.get('api/job/' + jobId).success(function(data, status, headers, config){
            $scope.job = data.result;
        });
    }
}]);

weixinApp.controller('resumeController', [
    '$scope', '$http', '$routeParams', '$location', '$window', 'constantsService', 'multiFormService',
    function($scope, $http, $routeParams, $location, $window, constantsService, multiFormService) {

    $scope.validated = false;

    $scope.resume = {
        name: "",
        mobile: "",
        experience: "",
        diploma : '',
        detail: ""
    };

    $scope.data = {
        experiences: [
            "1-2",
            "2-3",
            "3-5",
            "5-7",
            "8+"
        ],
        diploma : []
    };

    constantsService.getDiplomas().then(function(response) {
        var diplomas = response.data.diplomas;
        diplomas.shift(); //remove the first item which is "none"
        $scope.data.diploma = diplomas;
    });

    $scope.submitResume = function(){
    	$scope.validated = true;
        
    	if($scope.resumeForm.$valid) {
    		$('#loading').modal('show');

            var jobId = $routeParams.jobId;
            multiFormService.submitMultiFormWithFile($scope.resumeAttachment, "api/profile/" + $routeParams.jobId, JSON.stringify($scope.resume), function(data, status, headers, config){
        		$('#loading').modal('hide');
                $location.path("#/job/" + jobId);
            });
    	}
    };

    $scope.goBack = function() {
        $window.history.back();
    };

}]);
