var jobApp = angular.module('jobApp', ['ngResource','ui.bootstrap','ui.bootstrap-slider','simditor']);

jobApp.controller('jobController', ['$scope', 'jobService', function($scope, jobService) {
    $scope.job = {
        title: "",
        type: "talent",
        locations: {
            "上海" : true
        },
        diploma : 'none',
        experience : [3, 5],
        introduction: "",
        detail: ""
    };

    $scope.showExperience = function() {
        var from = $scope.job.experience[0];
        var to = $scope.job.experience[1];

        if(to == 16) {
           return from + "+ 年"
        } else if (from == to) {
            return from + " 年"
        }

        return from + " - " + to + " 年";
    }

    $scope.submitJob = function() {
        alert("fasdfasdf");
        jobService.submitNewJob($scope.job);
    }
}]);