"use strict";

angular.module('common').filter('trustAsHtml', ['$sce', function($sce){
    return function(text) {
        if(text) {
            return $sce.trustAsHtml(text);
        } else {
            return "";
        }

    };
}]);

angular.module('common').filter('timeString', function(){
     return function(time) {
        var currentTime = new Date(time);
        var hours = "0" + currentTime.getHours();
        var minutes = "0" + currentTime.getMinutes();
        var seconds = "0" + currentTime.getSeconds();

        return currentTime.getFullYear() + "/" + (currentTime.getMonth() + 1) + "/" + currentTime.getDate()
                        + " " + hours.slice(-2) + ":" + minutes.slice(-2);
    }
});

angular.module('common').filter('localized', function(labelTranslation){
    return function(key) {
        return labelTranslation[key];
    }
});

angular.module('common').filter('locationString', function(){
     return function(locations) {
        if(locations) {
            return locations.join(",");
        } else {
            return "";
        }
     }
});