"use strict";

angular.module('consoleApp').controller('jobEditController',[
'$scope', '$http', '$modal', '$routeParams', '$window', 'constantsService', 'userService', "jobService", "utils",
function($scope, $http, $modal, $routeParams, $window, constantsService, userService, jobService, utils) {

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
    constantsService.getOfficeLocations().then(function(data) {
        $scope.data.locations = data.locations;
    });

    userService.getUsers().then(function(data) {
        $scope.data.users = data.users;
    });

    constantsService.getDiplomas().then(function(data) {
        $scope.data.diploma = data.diplomas;
    });

    //load job
    var jobId = $scope.jobId;
    if(jobId !== undefined && jobId !== "0") {
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
            jobService.newJob($scope.jobId, $scope.job).then(function(){
                showConfirmDialog();
            });
        }
    };

    $scope.showTitle = function() {
        if($scope.jobId === 0) {
            return "新建职位";
        } else {
            return "编辑职位";
        }
    };

    var previousExperience;
    $scope.$watch("job.type", function(){

        //when select graduate, means No Experience Required.
        if($scope.job.type === "graduate") {
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
        if($scope.data.experience) {
            $scope.job.experienceFrom = $scope.data.experience[0];
            $scope.job.experienceTo = $scope.data.experience[1];
        }
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
 ['$log', '$scope', '$http', '$location', '$routeParams', '$window','$rootScope', '$modal', "jobService", 'applicationService', 'utils', 'userInfo',
     function($log, $scope, $http, $location, $routeParams, $window, $rootScope, $modal, jobService, applicationService, utils, userInfo) {

    $scope.total = 99; //given pagination control a chance to allow actual page selected
    $scope.pageSize = 1;

    $rootScope.hasPermission = userInfo;

    //$routeUpdate event will only be triggered when reloadOnSearch to false
    //$locationChangeSuccess can also be used for the similar result
    var oldPath = $location.path();
    var listener = $scope.$on('$locationChangeSuccess', function(){
        //When path has no change,
        //if user update only search parameters (from menu or other places) directly,
        //which will cause $scope search model out of sync with new search parameters,
        //Then, we should manually invoke search function
        var currentPath = $location.path();
        if(oldPath === currentPath && !angular.equals($location.search(), $scope.search)) {
            $scope.search = null; //reset search
            $scope.searchJob();
            oldPath = currentPath;
        }
    });

    $scope.clearSearch = function() {
        $scope.search = {
            page : 1
        };
    };

    $scope.pageChanged = function() {
        $scope.searchJob(false);
    };

    $scope.searchJob = function (reset) {

        $scope.searchOpen = false;

        $scope.search = $scope.search || angular.copy($location.search());

        if(reset || !$scope.search.page) {
            $scope.search.page = 1; //initialize page if not exist
        }

        var toSearch = utils.purifyObject($scope.search);

        //ignore "page"
        $scope.hasSearchCriteria = utils.checkSize(toSearch) > 1;

        jobService.searchJob(toSearch).then(function(data){
            $scope.jobs = data.jobs;
            $scope.total = data.total;
            $scope.pageSize = data.pageSize;
            $scope.pageTotal = data.total % data.pageSize ?  (data.total / data.pageSize + 1) : (data.total / data.pageSize);
        });

        $location.search($scope.search);
    };

    $scope.activateJob = function(job) {
        jobService.activateJob(job.id, !job.activated).then(function(){
            //do nothing here
        });

        job.activated = ! job.activated;
    };

    //load search result by default
    $scope.searchJob();

    //==================================================================================
    // Functions used in modal of profiles for specific Job
    //==================================================================================

    $scope.checkApplications = function(job) {

        //self protected
        if(!job.totalOfApplications) {
            return;
        }

        if(!job.showProfiles && !job.applications) {
            jobService.getProfiles(job.id).then(function(data){
                job.applications = data.applications;

                angular.forEach(job.applications, function(application) {
                    application.profile = data.profiles[application.profileId];
                });
            });
        }

        job.showProfiles = !job.showProfiles;
    };


     $scope.updateRate = function(application) {
         applicationService.updateRate(application.jobId, application.profileId, application.rate).then(function(data){
             //do nothing
         });
     };

     $scope.hoveringOver = function(value) {
         $scope.overStar = value;
         $scope.percent = 100 * (value / $scope.max);
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

    constantsService.getDiplomas().then(function(data) {
        var diplomas = data.diplomas;
        diplomas.shift(); //remove the first item which is "none"
        $scope.data.diplomas = diplomas;
    });

    $scope.pageChanged = function() {
        searchProfile();
    };

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

    //Search profiles when controller is loaded.
    searchProfile();

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


angular.module('consoleApp').controller('userListController',
    ['$scope', '$http', 'userService', function($scope, $http, userService) {

    userService.getUsers().then(function(data){
       $scope.users = data.users;
    });

}]);

angular.module('consoleApp').controller('userController',
    ['$scope', '$routeParams', 'utils', 'userService', function($scope, $routeParams,  utils, userService) {

    $scope.userId = $routeParams.userId;


    $scope.user = {
        id : $scope.userId
    };

    $scope.goBack = function() {
        utils.goBack();
    };

    var userId = $scope.userId;
    if(userId !== undefined && userId !== "0") {
        userService.getUserInfo(userId).then(function(user){
            $scope.user = user;
        });
    }

    $scope.submit = function() {
        $scope.validated = true;
        if($scope.userForm.$valid) {
            userService.newUser($scope.user).then(function(){
                utils.goBack();
            }, function(reason){
                $scope.error = "新建用户出错，请检查是否同名。";
            });
        }
    };

}]);
