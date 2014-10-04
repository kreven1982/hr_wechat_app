angular.module('consoleApp').service('resumeService', ['$http', 'baseUrl', function($http, baseUrl){

      this.getResumeList = function (searchForm) {
    	  $http.get(baseUrl + 'api/resume/search', job).success(function(data, status, headers, config){
    		  console.log(data);
    	  });
      }
}]);