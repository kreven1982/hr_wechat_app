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