"use strict";

var commonModule = angular.module('common', []);

commonModule.constant("labelTranslation", {
    "none" : "不限",
    "associate" : "大专",
    "bachelor" : "本科",
    "master" : "硕士",
    "doctor" : "博士",
    "mba" : "MBA",
    "talent" : "社会招聘",
    "graduate" : "校园招聘"
});


commonModule.constant("profileConstant", {
    experiences: [
        "0-1",
        "1-2",
        "2-3",
        "3-5",
        "5-7",
        "8+"
    ]
});