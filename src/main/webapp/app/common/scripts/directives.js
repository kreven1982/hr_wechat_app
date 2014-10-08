var commonModule = angular.module('common');

commonModule.directive('jobExperience', function(){
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

commonModule.directive('jobLocations', function(){
     return {
        restrict: "AE",
        scope: {
           jobLocations : "="
        },
        link : function(scope, element, attrs) {

            scope.$watch("jobLocations", function(){
                 element.text(scope.jobLocations.join(","));
            });
        }
     }
});

commonModule.directive('diploma', function(diplomas){
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

commonModule.directive('recruitTypes', function(recruitTypes){
     return {
        restrict: "AE",
        scope: {
           type : "="
        },
        link : function(scope, element, attrs) {

            scope.$watch("type", function(){
                 element.text(recruitTypes[scope.type]);
            });
        }
     }
});

commonModule.directive('time', function(){
     return {
        restrict: "AE",
        scope: {
           time : "="
        },
        link : function(scope, element, attrs) {

            scope.$watch("time", function(){

                var currentTime = new Date(scope.time);
                var hours = "0" + currentTime.getHours();
                var minutes = "0" + currentTime.getMinutes();
                var seconds = "0" + currentTime.getSeconds();

                var timeString = currentTime.getFullYear() + "/" + (currentTime.getMonth() + 1) + "/" + currentTime.getDate()
                                + " " + hours.slice(-2) + ":" + minutes.slice(-2);

                element.text(timeString);
            });
        }
     }
});

commonModule.directive('formRequired', function(){
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