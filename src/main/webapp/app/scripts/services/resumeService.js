angular.module('jobApp').service('resumeService', ['$http', 'baseUrl', function($http, baseUrl){

      this.getResumeList = function (searchForm) {
    	  $http.get(baseUrl + 'm//management/resume/search', job).success(function(data, status, headers, config){
    		  console.log(data);
    	  });
      }
}]);