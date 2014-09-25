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
       showConfirmDialog();
    $scope.submitJob = function() {


        if($scope.jobForm.$valid) {
            $http.post(baseUrl + 'm/management/job', $scope.job).success(function(data, status, headers, config){
                 console.log(data);
                 alert("新建成功");
            });
        } else {
            alert("form is not valid");
        }
        return;
    }

    $scope.showExperience = function() {

        var from =  $scope.data.experience[0];
        var to = $scope.data.experience[1];

        if(to == 16) {
           return from + "+ 年"
        } else if (from == to) {
            return from + " 年"
        }

        return from + " - " + to + " 年";
    }

    $scope.$watch("locations", function(){
        $scope.job.locations = convertLocations($scope.locations);
    }, true);

    $scope.$watch("data.experience", function(){
        $scope.job.experienceFrom = $scope.data.experience[0];
        $scope.job.experienceTo = $scope.data.experience[1];
    }, true);

    function showConfirmDialog() {

        alert(baseUrl + 'app/views/confirm.dialog.html');
         var modalInstance = $modal.open({
            templateUrl: baseUrl + 'app/views/confirm.dialog.html',
            size : 'lg'
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

jobApp.controller('bannerController', ['$scope','$modal','baseUrl', function($scope, $modal, baseUrl) {
	$scope.isCollapsed = true;
       
	/*$scope.openSearchResume = function(){
		var modalInstance = $modal.open({
			templateUrl: baseUrl + 'app/views/searchResume.dialog.html',
		    size : 'lg'
		});
	};*/
}]);

jobApp.controller('jobListController', 'baseUrl', ['$scope', function($scope, baseUrl) {
       $http.get(baseUrl + 'm/management/job', job).success(function(data, status, headers, config){
            console.log(data);
       });
}]);

jobApp.directive('optionRequired', function(){
     return {
        restrict: "AE",
        scope: {
           locations : "="
        },
        link : function(scope, element, attrs) {

            scope.$watch("locations", function(){
               console.log(scope.locations);
            }, true);
        }
     }
});

/*jobApp.controller('resumeSearchController', ['$scope', function($scope) {
	$scope.openSearchResume = function(){
		var modalInstance = $modal.open({
	        templateUrl: baseUrl + 'app/views/searchResume.dialog.html',
	        size : 'lg'
	     });
	};
}]);*/

jobApp.controller('resumeSearchController', ['$scope','$modal','baseUrl', function($scope, $modal, baseUrl) {
	$scope.isCollapsed = true;
       
	$scope.openSearchResume = function(){
		var modalInstance = $modal.open({
			templateUrl: baseUrl + 'app/views/searchResume.dialog.html',
		    size : 'lg'
		});
	};
}]);