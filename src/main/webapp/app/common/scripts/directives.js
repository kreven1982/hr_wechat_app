"use strict";

angular.module('common').directive('jobExperience', function(){
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

angular.module('common').directive('formRequired', function(){
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


angular.module('common').directive('fileModel', ['$parse', function ($parse) {
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