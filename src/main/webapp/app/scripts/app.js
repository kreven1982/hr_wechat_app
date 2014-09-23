var jobApp = angular.module('jobApp', ['ui.bootstrap','ui.bootstrap-slider','simditor']);

jobApp.controller('jobController', ['$scope', '$http','jobService', function($scope, $http, jobService) {

    $scope.job = {
        title: "",
        type: "talent",
        diploma : 'none',
        experience : [3, 8],
        locations : {
           "上海" : true
        },
        introduction: "",
        content: ""
    };

    //Initialize locations
    $http.get('/m/management/job/locations').success(function(data, status, headers, config){
         $scope.locations = data.locations;
    });

    $scope.submitJob = function() {
        var job = angular.copy($scope.job);
        job.experienceFrom = job.experience[0];
        job.experienceTo = job.experience[1];
        job.locations = convertLocations(job.locations);
        delete job.experience;

        $http.post('/m/management/job', job).success(function(data, status, headers, config){
             console.log(data);
        });
    }

    $scope.showExperience = function() {

        var from =  $scope.job.experience[0];
        var to = $scope.job.experience[1];

        if(to == 16) {
           return from + "+ 年"
        } else if (from == to) {
            return from + " 年"
        }

        return from + " - " + to + " 年";
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
     $.isCollapsed = true;
}]);