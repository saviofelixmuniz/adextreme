angular.module('adExtreme')
.directive('errorMessage', function () {
	return {
		restrict: 'AE',
		scope: {
			form: "="
		},
		templateUrl: 'templates/directives/common/error-message.html'
	}
})