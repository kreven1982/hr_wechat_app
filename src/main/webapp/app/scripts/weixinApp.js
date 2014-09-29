var weixinApp = angular.module('weixinApp', []);

weixinApp.controller('weixinResumeController', ['$scope', '$http', function($scope, $http) {
    $scope.resume = {
        name: "",
        mobile: "",
        experience: "none",
        diploma : 'none',
        detail: ""
    };
    
    $scope.submitResume = function(){
    	var fd = new FormData();
    	fd.append('file', $scope.resumeAttachment);
    	fd.append('data', JSON.stringify($scope.resume))
    	$http.post("/ROOT/m/resume/submit", fd, {
    		transformRequest: angular.identity,
    		headers: {'Content-Type': undefined}
    	})
    	.success(function(data, status, headers, config){
    		console.log(data);
    	})
    	.error(function(data, status, headers, config){
    		console.log(status);
    	});
    }
    
}]);

weixinApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

