angular.module('adExtreme')
.controller('UserHomeCtrl', function ($scope, User, Advertisement, $rootScope) {
	
	var GREEN_COLOR = '#5CB85C';
	var RED_COLOR = '#D9534F';

	$scope.current = {adPage : 1,
					  transactionPage : 1};

	$scope.loadUserInfo = function() {
		updateBalance();
		getUserAds();
		loadTransactions();
	}

	function updateBalance() {
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

	function getUserAds() {
		$mask();
		Advertisement.getByUser($rootScope.currentUser.id)
		.then (
			function success (response) {
				$unmask();	
				$scope.userAds = response.data;
				$scope.pageChanged('ads');
			}, function error (response) {
				$unmask();
				$defaultError();
			}
		)
	}

	function loadTransactions() {
		$mask();
		Advertisement.getTransactions($rootScope.currentUser.id)
		.then (
			function success (response) {
				$unmask();
				$scope.transactions = response.data;
				$scope.pageChanged('transaction');
			}, function error (response) {
				$unmask();
				$defaultError();
			});
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

	$scope.getTransactionColor = function(transaction) {
		if (isTransactionOwner(transaction)) {
			return GREEN_COLOR; //green	
		}
		else 
			return RED_COLOR; //red
	}

	$scope.getTransactionSign = function(transaction) {
		if (isTransactionOwner(transaction)) 
			return '+';
		else
			return '-';
	}

	$scope.getPages = function() {
		if ($scope.transactions){
			return Math.ceil($scope.transactions.length / 4);
		}
	}

	$scope.pageChanged = function (paginationType) {
		if (paginationType == 'transaction')
			$scope.current.transactions = handlePages($scope.current.transactionPage, 4, $scope.transactions);
		else
			$scope.current.ads = handlePages($scope.current.adPage, 8, $scope.userAds);
	}

	$scope.getAdStatus = function (ad) {
		if (!ad.available) {
			return 'Vendido';
		}
		else 
			return 'À venda';
	}

	$scope.getAdStatusStyle = function(ad) {
		if (!ad.available)
			return GREEN_COLOR;
		else
			return RED_COLOR;
	}

	function handlePages(page, nItems, list) {
		if (page) {
			var endIndex = page * nItems;
			var startIndex = (page - 1) * nItems;

			if (endIndex > list.length)
				endIndex = list.length;

			return list.slice (startIndex, endIndex);
		}
	}

	function isTransactionOwner (transaction) {
		return transaction.idOwner == $rootScope.currentUser.id;
	}

	function isCreditor () {
		return $rootScope.currentUser.credit >= 0;
	}

});