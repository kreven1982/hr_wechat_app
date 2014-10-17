"use strict";

angular.module('common').service('localeService', ['$http', function($http){

    this.getLabels = function() {
        return $http.get('api/labels');
    };

}]);

angular.module('common').service('constantsService', ['$http', function($http){

    this.getDiplomas = function() {
        return $http.get('api/diplomas');
    };

}]);

angular.module('common').service('multiFormService', ['$http', function ($http) {

    this.submitMultiFormWithFile = function(file, url, serializedData, successCallback, errorCallback){
        var formData = new FormData();
        formData.append('file', file);
        formData.append('data', serializedData);

        $http.post(url, formData, {

            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }

        }).success(function(data, status, headers, config){

            if(successCallback) {
                successCallback(data, status, headers, config);
            }

        }).error(function(data, status, headers, config){

                if(errorCallback) {
                errorCallback(data, status, headers, config);
            }

        });
    }

}]);
