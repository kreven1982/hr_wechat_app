"use strict";

angular.module('common').service('constantsService', ['$http', function($http){

    this.getDiplomas = function() {
        return $http.get('api/diplomas', { cache : true }).then(function(response) {
            return response.data;
        });
    };

    this.getOfficeLocations = function() {
        return $http.get('api/job/locations', { cache: true }).then(function(response){
            return response.data;
        });
    };

}]);

angular.module('common').service('utils', ['$rootScope', '$window', function($rootScope, $window){

    this.setTitle = function(title) {
        $rootScope.title = title;
    };

    //remove all empty string, null, false values from a map / object
    this.purifyObject = function(objectToBePurify) {
        var purifiedObject = angular.copy(objectToBePurify);

        angular.forEach(purifiedObject,function( value, key){
            if(!value) {
                delete purifiedObject[key];
            }
        });

        return purifiedObject;
    };

    this.checkSize = function(objectToTest) {
        return Object.keys( objectToTest ).length;
    };

    this.goBack = function() {
        $window.history.back();
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
    };

}]);
