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

      this.searchJob = function(searchCriteria) {

          var url = 'api/job/search?';

          angular.forEach(searchCriteria,function( value, key){
              if(value) {
                  url += key + "=" + value + "&";
              }
          });

          return $http.get(url).then(function(response) {
              return response.data;
          });
      };

      this.getJob = function(jobId) {
          return $http.get('api/job/' + jobId).then(function(response){
              return response.data.result;
          });
      }
}]);

angular.module('consoleApp').service('profileService', ['$http', function($http){

    this.searchProfile = function (searchCriteria) {

        var url =  'api/profile/search?';

        angular.forEach(searchCriteria,function( value, key){
            if(value) {
                url += key + "=" + value + "&";
            }
        });

        return $http.get(url).then(function(response){
            return response.data;
        });
    }

}]);


angular.module('consoleApp').factory('authInterceptor', [ '$q', '$window', function ($q, $window) {

    //to prevent alert multiple times
    var logout = false;

    return {
        response: function (response) {
            return response || $q.when(response);
        },
        responseError: function (rejection) {
            if(rejection.status === 401) {

                console.log("logout...");
                $window.location = "login";
                if(!logout) {
                    logout = true;
                    alert("你的session已过期或者无效,请重新登录!");
                }
            }
            return $q.reject(rejection);
        }
    }

}]);