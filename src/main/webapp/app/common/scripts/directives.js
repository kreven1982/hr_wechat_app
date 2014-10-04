var commonModule = angular.module('common', []);

commonModule.value("diplomas", {
                             none : "不限",
                             associate : "大专",
                             bachelor : "本科",
                             master : "硕士",
                             doctor : "博士"
                          });

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