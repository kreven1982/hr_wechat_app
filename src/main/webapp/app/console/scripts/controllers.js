"use strict";

angular.module('consoleApp').controller('jobController', ['$scope', '$http', '$modal', '$routeParams', '$window', 'constantsService', function($scope, $http, $modal, $routeParams, $window, constantsService) {

    $scope.jobId = $routeParams.jobId;

    $scope.job = {
        title: "",
        type: "talent",
        diploma : 'none',
        experienceFrom: 0,
        experienceTo: 0,
        locations : [],
        introduction: "",
        content: "",
        createTime: new Date().getTime()
    };

    $scope.data = {
        experience : [3, 8],
        locations : {},
        selectedLocations : {}
    };

    //Initialize locations
    $http.get('api/job/locations').success(function(data, status, headers, config){
         $scope.data.locations = data.locations;
    });

    constantsService.getDiplomas().then(function(response) {
        $scope.data.diploma = response.data.diplomas;
    });

    //load job
    var jobId = $scope.jobId;
    if(jobId != undefined && jobId != 0) {
        $http.get('api/job/' + jobId).success(function(data, status, headers, config){
            $scope.job = data.result;
            $scope.data.experience = [$scope.job.experienceFrom, $scope.job.experienceTo];

            _.each($scope.job.locations, function(location) {
               $scope.data.selectedLocations[location] = true;
            });
        });
    }

    $scope.submitJob = function() {
        $scope.validated = true;
        if($scope.jobForm.$valid) {
            $http.post('api/job/' + $scope.jobId, $scope.job).success(function(data, status, headers, config){
                 showConfirmDialog();
            });
        }
    };

    $scope.showTitle = function() {
        if($scope.jobId == 0) {
            return "新建职位";
        } else {
            return "编辑职位";
        }
    };

    $scope.$watch("data.selectedLocations", function(){
        $scope.job.locations = convertLocations($scope.data.selectedLocations);
    }, true);

    $scope.$watch("data.experience", function(){
        $scope.job.experienceFrom = $scope.data.experience[0];
        $scope.job.experienceTo = $scope.data.experience[1];
    }, true);


    //For save success confirm dialog
    $scope.newJob = function() {
         $window.location.href = '#/job/0';
         $scope.confirmDialog.dismiss();
    };

    $scope.goToList = function() {
        $window.location.href = '#/jobs';
        $scope.confirmDialog.dismiss();
    };

    function showConfirmDialog() {
         $scope.confirmDialog = $modal.open({
            templateUrl: 'app/console/views/job.confirm.dialog.html',
            scope: $scope,
            backdrop : 'static',
            keyboard : false,
            size : 'sm'
         });
    }

    $scope.preview = function() {
         $modal.open({
            templateUrl: 'app/console/views/job.preview.html',
            scope: $scope,
            windowClass: "preview",
            size : 'sm'
         });
    };

    function convertLocations(locations) {

        var selectedLocations = [];
        _.each( locations, function( val, key ) {
          if ( val ) {
            selectedLocations.push(key);
          }
        });

        return selectedLocations;
    }

}]);

angular.module('consoleApp').controller('jobListController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    //When use ng-switch, we follow best practice to have a dot to avoid child scope issue.
    $scope.page = {
        currentPage : $routeParams.page != null ? $routeParams.page : 1
    };

    var NO_JOB_INFO = "还没有职位信息,请点击菜单新建一个";
    $scope.message = NO_JOB_INFO;

    $scope.deleteJob = function(job) {
         if(confirm("你想删除该职位信息吗?\n" + job.title)) {
            $scope.jobs.splice(  $scope.jobs.indexOf(job), 1 );
         }
    };

    $scope.$watch("page.currentPage", function(){
           $scope.message = "正在加载中...";
           var currentPage = $scope.page.currentPage;
           $http.get('api/job/all?page=' + currentPage).success(function(data, status, headers, config){
               $scope.jobs = data.result;
               $scope.total = data.total;
               $scope.pageSize = data.pageSize;
               $scope.pageTotal = data.total % data.pageSize ?  (data.total / data.pageSize + 1) : (data.total / data.pageSize);
               $location.search({page : currentPage});
               $scope.message = NO_JOB_INFO;
           });
    },true);
}]);

angular.module('consoleApp').controller('jobSearchController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    $scope.currentPage = $routeParams.page != null ? $routeParams.page : 1;

    $scope.deleteJob = function(job) {
         if(confirm("你想删除该职位信息吗?\n" + job.title)) {
            $scope.jobs.splice(  $scope.jobs.indexOf(job), 1 );
         }
    };

    $scope.$watch("currentPage", function(){
           var currentPage = $scope.currentPage;
           $http.get('api/job/all?page=' + $scope.currentPage).success(function(data, status, headers, config){
               $scope.jobs = data.result;
               $scope.total = data.total;
               $scope.pageSize = data.pageSize;
               $scope.pageTotal = data.total % data.pageSize ?  (data.total / data.pageSize + 1) : (data.total / data.pageSize);
               $location.search({page : currentPage});
           });
    },true);

}]);

angular.module('consoleApp').controller('bannerController', ['$scope', '$http', '$window', function($scope, $http, $window) {
    $scope.isCollapsed = true;

    $http.get("api/user/info").success(function(data, status, headers, config){
        $scope.userName = data.result.userName;
    });

    $scope.logout = function() {

        $http.get("api/user/logout").success(function(data, status, headers, config){
            $window.location = "/login";
        });

    };
}]);

angular.module('consoleApp').controller('resumeListController', ['$scope','$http', function($scope, $http) {
     $http.get('api/profile/all').success(function(data, status, headers, config){
          $scope.resumes = data.result;
     });
}]);

angular.module('consoleApp').controller('resumeSearchController', ['$scope','$modal', function($scope, $modal) {

	$scope.openSearchResume = function(){
		var modalInstance = $modal.open({
			templateUrl: 'app/console/views/resume.search.dialog.html',
		    size : 'md',
		    controller: resumeSearchModalController
		});

		modalInstance.result.then(function (result) {
			console.log('Result is: ' + result.mobile);
		}, function (reason) {
			console.log('Modal dismissed at: ' + new Date() + '| Reason is: ' + reason);
		});
	};

	var resumeSearchModalController = function($scope, $modalInstance, $http, $window) {
		$scope.searchForm = {
			name: '',
			mobile: '',
			diploma: 'none',
			experience: 'none',
			keyword: ''
		};

		$scope.search = function () {
			/*$http.get('api/resume/all', $scope.searchForm).success(function(data, status, headers, config){
				$scope.resumes = data.result;
			});*/

			$window.location.href = '#/resumes';
			$modalInstance.close($scope.searchForm);
		};

		$scope.close = function () {
			$modalInstance.dismiss('cancel');
		};
	};
}]);
