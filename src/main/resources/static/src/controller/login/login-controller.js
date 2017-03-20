angular.module('adExtreme')
.controller('LoginCtrl', function ($scope, User, $location, $rootScope, PROPERTIES) {
	$scope.alerts = [];
	$scope.remember = false;

	$scope.login = function () {
		verifyEmptyInput();
		validateUser($scope.email,$scope.password);
	}

	$scope.closeAlert = function (index) {
		$scope.alerts.splice(index,1);
	}

	function verifyEmptyInput () {
		if (!$scope.email && !$scope.password) {
			addAlert("Os campos de usuário e senha estão vazios, preencha-os para continuar");
		}
		else if (!$scope.email) {
			addAlert("O campo de usuário está vazio, preencha-o para continuar");
		}
		else if (!$scope.password) {
			addAlert("O campo de usuário está vazio, preencha-o para continuar");
		}
	}

	function addAlert (msg) {
		$scope.alerts = [];
		$scope.alerts.push({msg : msg});
	}

	function validateUser (email,password) {
		User.login(email, password/*, $scope.remember*/)
		.then (
			function (response) {
				localStorage.setItem("adExtremeUserToken", response.data.token);
				$rootScope.currentUser = response.data.user;
                $rootScope.loggedIn = true;
				$location.path("/user-home");
		},
			function (response) {
				if (response.status == 404) addAlert("Usuário não encontrado.");
				else if (response.status == 401) addAlert("Login ou senha estão incorretos.");
                else if (response.status == 400) addAlert("Login ou senha vazios.");
				else addAlert("Falha de comunicação com o servidor, tente novamente.")
		});
	}
});