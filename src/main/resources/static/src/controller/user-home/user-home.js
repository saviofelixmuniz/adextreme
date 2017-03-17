angular.module('adExtreme')
.controller('UserHomeCtrl', function ($scope, User, $rootScope) {
	$scope.updateBalance = function() {
		console.log($rootScope.currentUser);
		User.getBalance($rootScope.currentUser.id)
		.then ( 
			function success (response) {
				$rootScope.currentUser.credit = response.data;
			}, function error (response) {
				$defaultError();
			}
		);
	}

	$scope.getClass = function () {
		if (isCreditor())
			return 'positive-balance';
		else
			return 'negative-balance';
	}

	$scope.getBalanceStatus = function() {
		if (isCreditor()) 
			return "C";
		else 
			return "D";
	}

	function isCreditor () {
		return $rootScope.currentUser.credit >= 0;
	}
});