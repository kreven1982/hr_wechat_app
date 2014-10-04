var consoleApp = angular.module('consoleApp', ['ngRoute','ui.bootstrap','ui.bootstrap-slider','simditor']);

consoleApp.value("baseUrl", "./");
consoleApp.value("diplomas", {
                             none : "不限",
                             associate : "大专",
                             bachelor : "本科",
                             master : "硕士",
                             doctor : "博士"
                          });

consoleApp.config(function($routeProvider) {
    $routeProvider.when('/', {
            templateUrl : 'app/views/job.list.html',
            controller  : 'jobListController'
        }).when('/job/:jobId', {
            templateUrl : 'app/views/job.edit.html',
            controller  : 'jobController'
        });
});

consoleApp.controller('jobController', ['$scope', '$http', '$modal', '$routeParams', '$window', 'jobService', 'diplomas', function($scope, $http, $modal, $routeParams, $window, jobService, diplomas) {

    $scope.jobId = $routeParams.jobId;

    $scope.job = {
        title: "",
        type: "talent",
        diploma : 'none',
        experienceFrom: 0,
        experienceTo: 0,
        locations : [],
        introduction: "",
        content: ""
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
    $http.get('m/management/job/locations').success(function(data, status, headers, config){
         _.each(data.locations, function(location) {
              $scope.data.locations[location] = false;
         });
    });

    $scope.submitJob = function() {
        $scope.validated = true;
        if($scope.jobForm.$valid) {
            showConfirmDialog();
            $http.post('m/management/job/' + $scope.jobId, $scope.job).success(function(data, status, headers, config){
                 console.log(data);
            });

        }
    };

    $scope.showDiploma = function(key) {
        return $scope.data.diploma[key];
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
           $http.get('m/management/job/' + jobId).success(function(data, status, headers, config){
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
        $window.location.href = '#/';
        $scope.confirmDialog.dismiss();
    };

    $scope.preview = function() {
         $modal.open({
            templateUrl: 'app/views/job.preview.html',
            scope: $scope,
            windowClass: "preview",
            size : 'sm'
         });
    };

    function showConfirmDialog() {
         $scope.confirmDialog = $modal.open({
            templateUrl: 'app/views/confirm.dialog.html',
            scope: $scope,
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

consoleApp.controller('jobListController', ['$scope', '$http', function($scope, $http) {

       $http.get('m/management/job/all').success(function(data, status, headers, config){
            $scope.jobs = data.result;
       });

}]);

consoleApp.controller('bannerController', ['$scope', function($scope) {
       $scope.isCollapsed = true;
}]);

consoleApp.directive('jobExperience', function(){
     return {
        restrict: "AE",
        scope: {
           from : "=",
           to : "="
        },
        link : function(scope, element, attrs) {

            scope.$watchCollection("[from, to]", function(){

                var from =  scope.from;
                var to = scope.to;
                var experienceText = from + " - " + to + " 年";

                if(to == 16) {
                   experienceText = from + "+ 年"
                } else if (from == to) {
                   experienceText = from + " 年"
                }

                element.text(experienceText);
            });
        }
     }
});

consoleApp.directive('jobLocations', function(){
     return {
        restrict: "AE",
        scope: {
           locations : "="
        },
        link : function(scope, element, attrs) {

            scope.$watch("locations", function(){
                 element.text(scope.locations.join(","));
            });
        }
     }
});


consoleApp.directive('jobDiploma', function(diplomas){
     return {
        restrict: "AE",
        scope: {
           diploma : "="
        },
        link : function(scope, element, attrs) {

            scope.$watch("diploma", function(){
                 element.text(diplomas[scope.diploma]);
            });
        }
     }
});

consoleApp.directive('formRequired', function(){
     return {
        restrict: "AE",
        require: '?ngModel',
        scope: {
           ngModel : "="
        },
        link : function(scope, element, attrs, ngModel) {
            scope.$watch("ngModel", function(){

                if(_.isEmpty(scope.ngModel)) {
                    ngModel.$setValidity("required", false);
                } else {
                    ngModel.$setValidity("required", true);
                }

            }, true);
        }
     }
});

consoleApp.controller('resumeSearchController', ['$scope','$modal', function($scope, $modal) {

	$scope.openSearchResume = function(){
		var modalInstance = $modal.open({
			templateUrl: 'app/views/searchResume.dialog.html',
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
			/*$http.get('m/management/resume/search', $scope.searchForm).success(function(data, status, headers, config){
				console.log(data);
			});*/
			
			$window.location.href = 'm/management/resume/list';
			//$modalInstance.close($scope.searchForm);
		};

		$scope.close = function () {
			$modalInstance.dismiss('cancel');
		};
	};
}]);

