var jobApp = angular.module('jobApp', ['ui.bootstrap','ui.bootstrap-slider','simditor']);

jobApp.value("baseUrl", $("base").attr("href"));

jobApp.controller('jobController', ['$scope', '$http', '$modal', 'jobService', 'baseUrl', function($scope, $http, $modal, jobService, baseUrl) {

    $scope.job = {
        title: "",
        type: "talent",
        diploma : 'none',
        experienceFrom: 0,
        experienceTo: 0,
        locations : null,
        introduction: "",
        content: ""
    };

    $scope.data = {
        experience : [3, 8],
        locations : {}
    };

    //Initialize locations
    $http.get(baseUrl + 'm/management/job/locations').success(function(data, status, headers, config){
         _.each(data.locations, function(location) {
              $scope.data.locations[location] = false;
         });
    });


    $scope.submitJob = function() {
        $scope.validated = true;
        if($scope.jobForm.$valid) {
            showConfirmDialog();
            $http.post(baseUrl + 'm/management/job', $scope.job).success(function(data, status, headers, config){
                 console.log(data);
            });

        } else {

        }
    };

    $scope.showExperience = function() {

        var from =  $scope.data.experience[0];
        var to = $scope.data.experience[1];

        if(to == 16) {
           return from + "+ 年"
        } else if (from == to) {
            return from + " 年"
        }

        return from + " - " + to + " 年";
    };

    $scope.$watch("data.locations", function(){
        $scope.job.locations = convertLocations($scope.data.locations);
    }, true);

    $scope.$watch("data.experience", function(){
        $scope.job.experienceFrom = $scope.data.experience[0];
        $scope.job.experienceTo = $scope.data.experience[1];
    }, true);

    $scope.newJob = function() {
        alert("new job");
    };

    $scope.goToList = function() {
        alert("go to list");
    };

    function showConfirmDialog() {
         $modal.open({
            templateUrl: baseUrl + 'app/views/confirm.dialog.html',
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

jobApp.controller('bannerController', ['$scope', function($scope) {
       $scope.isCollapsed = true;
}]);

//jobApp.controller('jobListController', ['$scope', 'baseUrl', function($scope, baseUrl) {
//       $http.get(baseUrl + 'm/management/job', job).success(function(data, status, headers, config){
//            console.log(data);
//       });
//}]);

jobApp.directive('formRequired', function(){
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

jobApp.controller('resumeSearchController', ['$scope','$modal','baseUrl', function($scope, $modal, baseUrl) {

	$scope.openSearchResume = function(){
		var modalInstance = $modal.open({
			templateUrl: baseUrl + 'app/views/searchResume.dialog.html',
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
			/*$http.get(baseUrl + 'm/management/resume/search', $scope.searchForm).success(function(data, status, headers, config){
				console.log(data);
			});*/
			
			$window.location.href = baseUrl + 'm/management/resume/list';
			//$modalInstance.close($scope.searchForm);
		};

		$scope.close = function () {
			$modalInstance.dismiss('cancel');
		};
	};
}]);

