var app = angular.module("lambda.bilan", ["ngCookies"]);

app.controller("logoutController",
    ['$scope','utils','security','$cookies',
        function ($scope,utils,security,$cookies ) {
            $cookies.remove("user");
            utils.redirectTo("/login.html");
        }])
;

