"use strict";

var weixinApp = angular.module('weixinApp');

weixinApp.controller('jobListController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    //When use ng-switch, we follow best practice to have a dot to avoid child scope issue.
    $scope.page = {
        currentPage : $routeParams.page != null ? $routeParams.page : 1
    };

    $scope.$watch("page.currentPage", function(){
           var currentPage = $scope.page.currentPage;
           $http.get('api/job/all?page=' + currentPage).success(function(data, status, headers, config){
               $scope.jobs = data.result;
               $scope.total = data.total;
               $scope.pageSize = data.pageSize;
               $scope.pageTotal = data.total % data.pageSize ?  (data.total / data.pageSize + 1) : (data.total / data.pageSize);
               $location.search({page : currentPage});
           });
    },true);

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
