"use strict";

var weixinApp = angular.module('weixinApp');

weixinApp.controller('jobListController', ['$scope', '$http', '$location', '$routeParams', 'constantsService', function($scope, $http, $location, $routeParams, constantsService) {

    $scope.data = {};
    $scope.search = $location.search();

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
       $scope.jobs = data.result;
    });

    $scope.searchJob = function() {
        var toSearch = angular.copy($scope.search);
        $location.search(toSearch);
    };
}]);

weixinApp.controller('jobController', ['$scope', '$http', '$routeParams', '$window', function($scope, $http, $routeParams, $window) {

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

            var jobId = $scope.resume.jobId;
            multiFormService.submitMultiFormWithFile($scope.resumeAttachment, "api/profile/" + jobId, JSON.stringify($scope.resume), function(data, status, headers, config){
        		$('#loading').modal('hide');
                $location.path("#/job/" + jobId);
            });
    	}
    };

    $scope.goBack = function() {
        $window.history.back();
    };

}]);
