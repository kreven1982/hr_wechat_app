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
    console.log(jobId);
    if(jobId != undefined && jobId != 0) {
        $http.get('api/job/' + jobId).success(function(data, status, headers, config){
            $scope.job = data.result;
        });
    }
}]);

weixinApp.controller('weixinResumeController', ['$scope', '$http', function($scope, $http) {
    $scope.resume = {
        name: "",
        mobile: "",
        experience: "",
        diploma : '',
        detail: ""
    };

    $scope.submitResume = function(){
    	$scope.validated = true;

    	if($scope.form.$valid) {
    		$('#loading').modal('show');

    		var fd = new FormData();
        	fd.append('file', $scope.resumeAttachment);
        	fd.append('data', JSON.stringify($scope.resume))
        	$http.post("api/resume/submit", fd, {
        		transformRequest: angular.identity,
        		headers: {'Content-Type': undefined}
        	})
        	.success(function(data, status, headers, config){
        		console.log(data);
        		$('#loading').modal('hide');
        	})
        	.error(function(data, status, headers, config){
        		console.log(status);
        	});
    	}
    }

}]);
