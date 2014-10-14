"use strict";

angular.module('common').service('localeService', ['$http', '$q', function($http , $q){

    this.getLabels = function() {
        return $http.get('api/labels', {cache : true});
    }
}]);