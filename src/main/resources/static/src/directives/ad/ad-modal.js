angular.module("adExtreme")
.controller("AdModalCtrl", function ($scope, $rootScope, $uibModalInstance, Advertisement) {
	$scope.currentAd.description = "anuncio legal";
	$scope.currentAd.contact = "(83) 98181-5678";

	$scope.cancel = function() {
		console.log($scope.currentAd);
		$uibModalInstance.dismiss();
	}

	$scope.submit = function() {
		$warningBox("Comprar?","Deseja confirmar sua compra?", buy)
	}

	function buy () {
		Advertisement.handleTransaction($rootScope.currentUser.id, $scope.currentAd.id)
		.then (function success (response) {
			$rootScope.currentUser.credit -= $scope.currentAd.price;
		}, function error (response) {
			$defaultError();
		});

		$uibModalInstance.close();
	}
});