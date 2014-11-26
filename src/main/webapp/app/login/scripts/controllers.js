"use strict";

angular.module('loginApp').controller('loginController', ['$scope','$http','$window', function($scope, $http, $window) {


    $scope.login = function() {
        $scope.validated = true;
        if($scope.loginForm.$valid) {
            $http.get("api/user/login?userName=" + $scope.userName + "&password=" + $scope.password).success(function(data, status, headers, config){
                 if(data.result === true) {
                     $window.location = "./console";
                 } else {
                     $scope.authenticationError = true;
                 }
            });
        }
    };

}]);