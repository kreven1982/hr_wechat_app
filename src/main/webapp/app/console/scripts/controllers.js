"use strict";

angular.module('consoleApp').controller('jobController',[
'$scope', '$http', '$modal', '$routeParams', '$window', 'constantsService', "jobService", "utils",
function($scope, $http, $modal, $routeParams, $window, constantsService, jobService, utils) {

    $scope.jobId = $routeParams.jobId;

    $scope.job = {
        title: "",
        type: "talent",
        diploma : 'none',
        experienceFrom: 0,
        experienceTo: 0,
        locations : [],
        introduction: "",
        content: "",
        createTime: new Date().getTime()
    };

    $scope.data = {
        experience : [3, 8],
        locations : {},
        selectedLocations : {}
    };

    //Initialize locations
    $http.get('api/job/locations').success(function(data, status, headers, config){
         $scope.data.locations = data.locations;
    });

    constantsService.getDiplomas().then(function(response) {
        $scope.data.diploma = response.data.diplomas;
    });

    //load job
    var jobId = $scope.jobId;
    if(jobId != undefined && jobId != 0) {
        jobService.getJob(jobId).then(function(data){
            $scope.job = data;
            $scope.data.experience = [$scope.job.experienceFrom, $scope.job.experienceTo];

            angular.forEach($scope.job.locations, function(location) {
               $scope.data.selectedLocations[location] = true;
            });
        });
    }

    $scope.submitJob = function() {
        $scope.validated = true;
        if($scope.jobForm.$valid) {
            $http.post('api/job/' + $scope.jobId, $scope.job).success(function(data, status, headers, config){
                 showConfirmDialog();
            });
        }
    };

    $scope.showTitle = function() {
        if($scope.jobId == 0) {
            return "新建职位";
        } else {
            return "编辑职位";
        }
    };

    var previousExperience;
    $scope.$watch("job.type", function(){

        //when select graduate, means No Experience Required.
        if($scope.job.type == "graduate") {
            previousExperience = $scope.data.experience;
            $scope.experienceEnabled = false;
            $scope.data.experience = [0, 0];
        } else {
            $scope.experienceEnabled = true;
            $scope.data.experience = previousExperience;
        }

    }, true);

    $scope.$watch("data.selectedLocations", function(){
        $scope.job.locations = convertLocations($scope.data.selectedLocations);
    }, true);

    $scope.$watch("data.experience", function(){
        $scope.job.experienceFrom = $scope.data.experience[0];
        $scope.job.experienceTo = $scope.data.experience[1];
    }, true);


    //For save success confirm dialog
    $scope.newJob = function() {
         $window.location.href = '#/job/0';
         $scope.confirmDialog.dismiss();
    };

    $scope.goToList = function() {
        $window.location.href = '#/jobs';
        $scope.confirmDialog.dismiss();
    };

    $scope.goBack = function() {
        utils.goBack();
    };

    function showConfirmDialog() {
         $scope.confirmDialog = $modal.open({
            templateUrl: 'app/console/views/job.confirm.dialog.html',
            scope: $scope,
            backdrop : 'static',
            keyboard : false,
            size : 'sm'
         });
    }

    $scope.preview = function() {
         $modal.open({
            templateUrl: 'app/console/views/job.preview.html',
            scope: $scope,
            windowClass: "preview",
            size : 'sm'
         });
    };

    function convertLocations(locations) {

        var selectedLocations = [];
        angular.forEach( locations, function( val, key ) {
          if ( val ) {
            selectedLocations.push(key);
          }
        });

        return selectedLocations;
    }

}]);

angular.module('consoleApp').controller('jobListController',
 ['$log', '$scope', '$http', '$location', '$routeParams', '$rootScope', '$modal', "jobService", 'utils', 'userInfo',
     function($log, $scope, $http, $location, $routeParams, $rootScope, $modal, jobService, utils, userInfo) {

    $log.debug("in console App jobListController");

    $scope.total = 99; //given pagination control a chance to allow actual page selected
    $scope.pageSize = 1;

    $scope.search = $location.search();

    if(!$scope.search.page) {
        $scope.search.page = 1; //initialize page if not exist
    }

    $rootScope.hasPermission = userInfo;

    $scope.deleteJob = function(job) {
         if(confirm("你想删除该职位信息吗?\n" + job.title)) {
            $scope.jobs.splice(  $scope.jobs.indexOf(job), 1 );
         }
    };

    $scope.$watch("search.page", function(){
        $scope.searchJob(false);
    },true);

    $scope.clearSearch = function() {
        $scope.search = {
            page : 1
        };
    };

    $scope.searchJob = function (reset) {
        $scope.searchOpen = false;

        var toSearch = utils.purifyObject($scope.search);

        if(reset) {
            toSearch.page = 1;
        }

        //ignore "page"
        $scope.hasSearchCriteria = utils.checkSize(toSearch) > 1;

        jobService.searchJob(toSearch).then(function(data){
            $scope.jobs = data.jobs;
            $scope.total = data.total;
            $scope.pageSize = data.pageSize;
            $scope.pageTotal = data.total % data.pageSize ?  (data.total / data.pageSize + 1) : (data.total / data.pageSize);

            $location.search(toSearch);
        });
    };

    $scope.activateJob = function(job) {
        jobService.activateJob(job.id, !job.activated).then(function(){
            //do nothing here
        });

        job.activated = ! job.activated;
    };

    $scope.deleteJob = function(job) {
        if(confirm("你想删除该职位信息吗?\n" + job.title)) {
            $scope.jobs.splice(  $scope.jobs.indexOf(job), 1 );
        }
    };


    //=======================================================
    // Functions used in modal of profiles for specific Job
    //=======================================================

    $scope.checkApplications = function(job) {

        //self protected
        if(!job.totalOfApplications) {
            return;
        }

        if(!job.showProfiles && !job.applications) {
            jobService.getProfiles(job.id).then(function(data){
                job.applications = data.applications;
                job.profiles = data.profiles;
            });
        }

        job.showProfiles = !job.showProfiles;
    };

}]);

angular.module('consoleApp').controller('bannerController',
 ['$scope', '$http', '$window', 'userService', function($scope, $http, $window, userService) {

    $scope.isCollapsed = true;
    userService.getUserInfo().then(function(userInfo) {
        $scope.userInfo = userInfo;
    });

    $scope.logout = function() {
        userService.logout();
        $window.location = "login";
    };
}]);

angular.module('consoleApp').controller('profileListController', [
    '$scope','$http', '$modal', '$location', 'profileService', 'constantsService', 'utils', 'profileConstant',
    function($scope, $http, $modal, $location, profileService, constantsService, utils, profileConstant) {

    $scope.total = 99; //given pagination control a chance to allow actual page selected
    $scope.pageSize = 1;

    $scope.search = $location.search();

    if(!$scope.search.page) {
        $scope.search.page = 1; //initialize page if not exist
    }

    $scope.data = {
        experiences : profileConstant.experiences
    };

    constantsService.getDiplomas().then(function(response) {
        var diplomas = response.data.diplomas;
        diplomas.shift(); //remove the first item which is "none"
        $scope.data.diplomas = diplomas;
    });

    $scope.$watch("search.page", function(){
        searchProfile();
    },true);

    $scope.searchProfile = function() {
        $scope.search.page = 1;  //reset to first page when criteria updated.
        searchProfile();
        $scope.profileSearchModal.dismiss();
    };

    $scope.closeSearch = function() {
        $scope.profileSearchModal.dismiss();
    };

    $scope.resetSearch = function() {
        $scope.search = {};
    };

    $scope.openSearch = function(){
        $scope.profileSearchModal = $modal.open({
            templateUrl: 'app/console/views/profile.search.dialog.html',
            size : 'md',
            scope: $scope
        });
    };

    function searchProfile() {

        var toSearch = utils.purifyObject($scope.search);

        //ignore "page"
        $scope.hasSearchCriteria = utils.checkSize(toSearch) > 1;

        profileService.searchProfile(toSearch).then(function(data){

            $scope.profiles = data.profiles;
            $scope.total = data.total;
            $scope.pageSize = data.pageSize;

            $location.search(toSearch);
        });
    }

}]);
