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
					console.log("USUARIO CADASTRADO: " + response.data.name);
                },
				function (response) {
                    console.log(response.status)
                    if (response.status == 409) console.log("Este email já esta em uso!");
                }
			);
	}
});