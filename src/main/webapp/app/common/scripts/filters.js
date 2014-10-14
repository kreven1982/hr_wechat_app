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

commonModule.filter('localized', function($timeout, localeService){

     var labels = null;
     var serviceInvoked = false;

    return function(key) {
        if( labels === null ) {
            if( !serviceInvoked ) {
                serviceInvoked = true;
                localeService.getLabels().then(function(result) {
                    labels = result.data;
                });
            }
            return "-";
        }
        else return labels[key];
    }
});

commonModule.filter('locationString', function(){
     return function(locations) {
        return locations.join(",");
     }
});