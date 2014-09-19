var jobApp = angular.module('jobApp', ['ui.bootstrap','ui.bootstrap-slider','simditor']);

jobApp.controller('JobController', ['$scope', function($scope) {
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
}]);