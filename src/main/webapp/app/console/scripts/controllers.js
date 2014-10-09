var consoleApp = angular.module('consoleApp');

consoleApp.controller('jobController', ['$scope', '$http', '$modal', '$routeParams', '$window', 'diplomas', function($scope, $http, $modal, $routeParams, $window, diplomas) {

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
        diploma : diplomas,
        experience : [3, 8],
        locations : {}
    };

    $scope.notSorted = function(obj){
        if (!obj) {
            return [];
        }
        return Object.keys(obj);
    };

    //Initialize locations
    $http.get('api/job/locations').success(function(data, status, headers, config){
         _.each(data.locations, function(location) {
              $scope.data.locations[location] = false;
         });
    });

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

    $scope.$watch( "jobId" ,function() {
        var jobId = $scope.jobId;
        if(jobId != undefined && jobId != 0) {
           $http.get('api/job/' + jobId).success(function(data, status, headers, config){
                $scope.job = data.result;
                $scope.data.experience = [$scope.job.experienceFrom, $scope.job.experienceTo];

                _.each($scope.job.locations, function(location) {
                   $scope.data.locations[location] = true;
                });
           });
        }
    }, true);

    $scope.$watch("data.locations", function(){
        $scope.job.locations = convertLocations($scope.data.locations);
    }, true);

    $scope.$watch("data.experience", function(){
        $scope.job.experienceFrom = $scope.data.experience[0];
        $scope.job.experienceTo = $scope.data.experience[1];
    }, true);

    $scope.newJob = function() {
         $window.location.href = '#/job/0';
         $scope.confirmDialog.dismiss();
    };

    $scope.goToList = function() {
        $window.location.href = '#/jobs';
        $scope.confirmDialog.dismiss();
    };

    $scope.preview = function() {
         $modal.open({
            templateUrl: 'app/console/views/job.preview.html',
            scope: $scope,
            windowClass: "preview",
            size : 'sm'
         });
    };

    function showConfirmDialog() {
         $scope.confirmDialog = $modal.open({
            templateUrl: 'app/console/views/job.confirm.dialog.html',
            scope: $scope,
            backdrop : 'static',
            size : 'sm'
         });
    }

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

consoleApp.controller('jobListController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

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

consoleApp.controller('jobSearchController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

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

consoleApp.controller('bannerController', ['$scope', function($scope) {
       $scope.isCollapsed = true;
}]);

consoleApp.controller('resumeListController', ['$scope','$http', function($scope, $http) {
     $http.get('api/resume/all').success(function(data, status, headers, config){
          $scope.resumes = data.result;
     });
}]);

consoleApp.controller('resumeSearchController', ['$scope','$modal', function($scope, $modal) {

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
