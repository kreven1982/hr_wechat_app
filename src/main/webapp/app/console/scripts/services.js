"use strict";

angular.module('consoleApp').service('resumeService', ['$http', function($http){

      this.getResumeList = function (searchForm) {
    	  $http.get('api/resume/search').success(function(data, status, headers, config){
    		  console.log(data);
    	  });
      }

}]);


angular.module('consoleApp').factory('authInterceptor', [ '$q', '$window', function ($q, $window) {

    return {
        response: function (response) {
            return response || $q.when(response);
        },
        responseError: function (rejection) {
            if(rejection.status === 401) {
                alert("你的session已过期或者无效,请重新登录!");
                $window.location = "login";
            }
            return $q.reject(rejection);
        }
    }

}]);