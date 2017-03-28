 angular.module('adExtreme')
.directive('navBar', function() {
	return {
		restrict: 'AE',
		scope: {
			loggedIn: '=',
			user: '='
		},
		templateUrl: '/templates/directives/nav-bar/nav-bar.html',
		controller: function ($element, $scope, $rootScope, $location, Advertisement, User, $uibModal) {
			$scope.alertMessages = [];

		    $scope.logout = function () {
		        User.logout();
		        localStorage.clear();
		        $rootScope.currentUser = {};
		        $rootScope.loggedIn = false;
		        $location.path('/login');
		    }

			function pushAlertMessage(alert) {
				var alertId = alert.id;
				var adId = alert.adId;
				var userId = alert.userToQualifyId;
				var transaction = alert.transactionType.toLowerCase();

				$mask();
				Advertisement.getSingle(adId)
				.then( function success (response) {

					var adTitle = response.data.title;
					
					User.getSingle(userId)
					.then( function success (response) {
						$unmask();

						var userName = response.data.name;

						$scope.alertMessages.push(
						{
							message: "Qualifique " + userName + " por sua " + transaction + "!",
							id : alertId,
							userQualifiedId : userId,
							userQualifiedName : userName,
							transaction : transaction
						});
					}, function error (response) {
						$unmask();
						$defaultError();
					});
				}, function error (response) {
					$unmask();
					$defaultError();
				});
			}

			$scope.rate = function(alert) {
		        var parent = this;

		        var modalInstance = $uibModal.open({
		          animation: true,
		          ariaLabelledBy: 'modal-title',
		          ariaDescribedBy: 'modal-body',
		          templateUrl: '/templates/directives/nav-bar/rating-modal.html',
		          controller: 'RatingModalCtrl',
		          resolve : {
		          	alert : function () {
		          		return alert;
		          	}
		          }
		        });

		        modalInstance.result.then( deleteAlert(alert));
			}

			$scope.$watch('$root.currentUser', function(user) {
				if (Object.keys(user).length != 0) {
					$scope.alerts = user.qualificationAlerts;

					$scope.alertMessages = [];
					
					$scope.alerts.forEach(function (alert) {
						pushAlertMessage(alert);
					});
				}
			});


		}
	};
});