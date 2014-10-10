var consoleApp = angular.module('consoleApp');

consoleApp.service('resumeService', ['$http', function($http){

      this.getResumeList = function (searchForm) {
    	  $http.get('api/resume/search', job).success(function(data, status, headers, config){
    		  console.log(data);
    	  });
      }

}]);