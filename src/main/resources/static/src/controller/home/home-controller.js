angular.module('adExtreme')
.controller('homeController', function ($scope, $rootScope, User) {
    $rootScope.loggedIn = false;
    $rootScope.currentUser = {};

    $scope.logout = function () {
        User.logout();
    }
});