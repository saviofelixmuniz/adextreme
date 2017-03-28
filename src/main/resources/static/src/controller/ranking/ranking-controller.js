angular.module('adExtreme')
.controller('RankingCtrl', function ($scope, User) {
	function getAverageRating(user) {
		if (user.ratingCount != 0) 
			return (user.ratingSum / user.ratingCount);
		else 	
			return 0;
	}

	$scope.sortBy = function (option) {
		$scope.reverse = ($scope.sortOption === option) ? !$scope.reverse : false;
		$scope.sortOption = option;
	}

	$scope.loadUsers = function () {
		User.getAll()
		.then(function success (response) {
			$scope.users = response.data;

			$scope.users.forEach(function(user) {
				user.averageRating = getAverageRating(user);
			});
		}, function error (response) {
			$defaultError();
		});
	}
});