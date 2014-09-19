angular.module('jobApp').service('jobService', function(){

      this.getJob = function(jobId) {
           alert(jobId);
      };

      this.submitNewJob = function(job) {
           alert(job.title);
      };
});