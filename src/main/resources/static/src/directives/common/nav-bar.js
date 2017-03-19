angular.module('adExtreme')
.directive('navBar', function() {
	return {
		restrict: 'AE',
		scope: {
			loggedIn: '=',
			user: '='
		},
		templateUrl: '/templates/directives/common/nav-bar.html',
		controller: function ($element, $scope) {
			$scope.unmask = function () {
				$unmask();
			}
		}
	};
});