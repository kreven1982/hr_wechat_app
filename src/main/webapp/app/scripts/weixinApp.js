var weixinApp = angular.module('weixinApp', []);

weixinApp.controller('weixinController', ['$scope', function($scope) {
    $scope.resume = {
        name: "Jeff",
        mobile: "18812345678",
        experience: "3-5",
        diploma : '硕士',
        detail: "J2EE Specialist"
    };
}]);