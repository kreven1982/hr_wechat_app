"use strict";

angular.module('common').service('localeService', ['$http', function($http){

    this.getLabels = function() {
        return $http.get('api/labels');
    }
}]);