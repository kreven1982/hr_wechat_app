"use strict";

angular.module('consoleApp').service('userService', ['$http', function($http){

      this.getUserInfo = function() {
           return $http.get("api/user/info").then(function(response){
                                return response.data.result;
                         });
      };

      this.logout = function() {
        return $http.get("api/user/logout").then(function(){

        });
      }
}]);


angular.module('consoleApp').service('jobService', ['$http', function($http){

      this.searchJob = function(page, keyword) {

          return $http.get('api/job/search?page=' + page + "&keyword=" + (keyword || "")).then(function(response) {
              return response.data;
          });
      };

      this.getJob = function(jobId) {
          return $http.get('api/job/' + jobId).then(function(response){
              return response.data.result;
          });
      }
}]);

angular.module('consoleApp').service('resumeService', ['$http', function($http){

      this.getResumeList = function (searchForm) {
    	  $http.get('api/resume/search').success(function(data, status, headers, config){

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