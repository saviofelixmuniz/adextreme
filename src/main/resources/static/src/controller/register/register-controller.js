angular.module('adExtreme')
.controller('RegisterCtrl', function ($scope,$rootScope,$location, $http, User) {
	
	function verifyPasswords () {
		return $scope.user.password == $scope.confirmPassword;
	}

	$scope.register = function (registerForm) {
		if (!verifyPasswords()) {
			registerForm.confirmPasswordForm.$error.mismatch = true;
			return;
		}
		registerUser();
	}

	$scope.clear = function(registerForm) {
		registerForm.$setUntouched();
		$scope.user = {};
		$scope.confirmPassword = '';
	}

	function registerUser () {
		User.register($scope.user.name, $scope.user.email, $scope.user.password, $scope.user.role)
		.then(
			function (response) {
				$successBox("Sucesso", "Usuário cadastrado com sucesso.");
				$location.path('/home')
            },
			function (response) {
                if (response.status == 409) $errorBox("E-mail já em uso", "Este e-mail já está em uso, favor escolher outro e-mail.");
                $scope.user.email = ""
            }
		);
	}
});