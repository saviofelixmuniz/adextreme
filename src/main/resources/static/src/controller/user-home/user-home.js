angular.module('adExtreme')
.controller('UserHomeCtrl', function ($scope, User, Advertisement, $rootScope) {
	var mask = true;
	$scope.userAds = [{
		date:1489838280960,
		id:2,
		idOwner:1,
		note:"",
		owner:"Savio",
		postDate:"18-mar-2017 08:58:00",
		price:2000,
		title:"ruim",
		type:"movel"
	}];

	$scope.updateBalance = function() {
		$mask();
		User.getBalance($rootScope.currentUser.id)
		.then ( 
			function success (response) {
				$unmask();
				$rootScope.currentUser.credit = response.data;
			}, function error (response) {
				$unmask();
				$defaultError();
			}
		);
	}

	$scope.getUserAds = function() {
		$mask();
		Advertisement.getByUser($rootScope.currentUser.id)
		.then (
			function success (response) {
				$unmask();	
				$scope.userAds = response.data;
				console.log('userAds');
				console.log($scope.userAds);
			}, function error (response) {
				$unmask();
				console.log($scope.ads);
				$defaultError();
			}
		)
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

	$scope.test = function () {
		if (mask) {
			$mask();
			mask = false;
		}
		else {
			$unmask();
			mask = true;
		}
	}

	$scope.log = function (ad,index) {
		console.log('ad')
		console.log(ad);
		console.log(index);

	}

	function isCreditor () {
		return $rootScope.currentUser.credit >= 0;
	}
});