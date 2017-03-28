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

	$scope.getTooltip = function () {
		return 'Você precisa ser pessoa jurídica para cadastrar este tipo de anúncio';
		//return $rootScope.currentUser.role == 'FISICA'? 'Você precisa ser pessoa jurídica para cadastrar este tipo de anúncio' : null;
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
            	//$location.path("/ad");
                $successBox("Sucesso", "Anúncio cadastrado.");
            }, function (response) {
				$defaultCommunicationError();
            });
	}
});