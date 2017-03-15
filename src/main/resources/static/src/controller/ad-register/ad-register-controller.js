angular.module('adExtreme')
.controller('AdRegisterCtrl', function ($scope, $http, $rootScope, Advertisement) {

	$scope.ad = { price : 0};

	$scope.confirmAd = function() {
		if($scope.ad.price == 0) {
			$warningBox("Atenção", "O preço do seu anúncio está de graça. Tem certeza que deseja continuar?",
						freePriceConfirmation);
		}

		else {
			register($scope.ad);
		}
	};

	$scope.clear = function (form) {
		form.$setUntouched();
		$scope.ad = {preco : 0};
	};

	function freePriceConfirmation () {
		register($scope.ad)
	}

	function register (ad) {
		ad.idOwner = $rootScope.currentUser.id;
        Advertisement.register(ad.title, ad.price, ad.type, ad.idOwner, $rootScope.currentUser.name)
            .then(function (response) {
                console.log("NOVO ANUNCIO: " + response.data.anuncio)
            }, function (response) {
				$defaultCommunicationError();
            });
	}
});