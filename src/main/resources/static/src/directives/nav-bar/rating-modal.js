angular.module('adExtreme')
.controller("RatingModalCtrl", function ($scope, $rootScope, $uibModalInstance, User, alert) {
	$scope.alert = alert;

	$scope.cancel = function() {
		$uibModalInstance.dismiss();
	}

	$scope.submit = function() {
		User.rate(alert.userQualifiedId, alert.id, $scope.rating)
		.then( function success (response) {
			$successBox("Sucesso!", "Usuário foi qualificado com sucesso. Obrigado!");
			$uibModalInstance.close();
		}, function error (response) {
			$defaultError();
		});
	}
});