"use strict";

angular.module('consoleApp').service('resumeService', ['$http', function($http){

      this.getResumeList = function (searchForm) {
    	  $http.get('api/resume/search', job).success(function(data, status, headers, config){
    		  console.log(data);
    	  });
      }

}]);


angular.module('consoleApp').factory('authInterceptor', [ '$q', '$window', function ($q, $window) {

    function success(response) {
        return response;
    }

    function error(response) {
        var status = response.status;

        if (status == 401) {
            $window.href = "/login";
            return;
        }
        // otherwise
        return $q.reject(response);

    }

    return function (promise) {
        return promise.then(success, error);
    }

}]);