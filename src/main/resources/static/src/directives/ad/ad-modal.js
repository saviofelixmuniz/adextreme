angular.module("adExtreme")
.controller("AdModalCtrl", function ($scope, $rootScope, $uibModalInstance, Advertisement) {
	$scope.currentAd.description = "anuncio legal";
	$scope.currentAd.contact = "(83) 98181-5678";

	$scope.cancel = function() {
		$uibModalInstance.dismiss();
	}

	$scope.submit = function() {
		$warningBox("Comprar?","Deseja confirmar sua compra?", buy)
	}

	function buy () {
		$mask();
		Advertisement.handleTransaction($rootScope.currentUser.id, $scope.currentAd.id)
		.then (function success (response) {
			$unmask();
			$rootScope.currentUser.credit -= $scope.currentAd.price;
		}, function error (response) {
			$unmask();
			$defaultError();
		});

		$uibModalInstance.close();
	}
});