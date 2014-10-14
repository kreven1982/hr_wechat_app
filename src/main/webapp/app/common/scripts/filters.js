var commonModule = angular.module('common');

commonModule.filter('trustAsHtml', ['$sce', function($sce){
    return function(text) {
        return $sce.trustAsHtml(text);
    };
}]);


commonModule.filter('timeString', function(){
     return function(time) {
        var currentTime = new Date(time);
        var hours = "0" + currentTime.getHours();
        var minutes = "0" + currentTime.getMinutes();
        var seconds = "0" + currentTime.getSeconds();

        var timeString = currentTime.getFullYear() + "/" + (currentTime.getMonth() + 1) + "/" + currentTime.getDate()
                        + " " + hours.slice(-2) + ":" + minutes.slice(-2);

        return timeString;
    }
});

commonModule.filter('recruitTypeString', function(recruitTypes){
     return function(type) {
        return recruitTypes[type];
     }
});


commonModule.filter('diplomaString', function(diplomas){
     return function(diploma) {
        return diplomas[diploma];
     }
});

commonModule.filter('localized', function(localeService){

     var labels;
     localeService.getLabels().then(function(result) {
        console.log(result.data);
        labels = result.data;
     });

     return function(key) {
        return labels[key];
     }
});

commonModule.filter('locationString', function(){
     return function(locations) {
        return locations.join(",");
     }
});