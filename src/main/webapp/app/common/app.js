var commonModule = angular.module('common', []);

commonModule.value("diplomas", {
                             none : "不限",
                             associate : "大专",
                             bachelor : "本科",
                             master : "硕士",
                             doctor : "博士"
                          });

commonModule.value("recruitTypes", {
                             talent : "社会招聘",
                             graduate : "校园招聘"
                          });