angular.module("adExtreme")
.factory("User", function UserFactory($http,PROPERTIES, $location, $rootScope) {
	return {
        getAll : function () {
            return $http.get(PROPERTIES.userPath);
        },

        getSingle : function (userId) {
            return $http.get(PROPERTIES.authenticatedRestPath + "/single/" + userId);
        },

        getBalance : function (userId) {
            return $http.get(PROPERTIES.authenticatedRestPath + "/balance/" + userId);
        },

        rate : function (userId, alertId, ratingValue) {
            return $http.post(PROPERTIES.authenticatedRestPath + "/qualificate/" + userId + "/" + alertId + "/" + ratingValue);
        },

        register : function (name, email, password, role) {
            var params = {
                name: name,
                password: password,
                email: email,
                role: role
            };
            return $http.post(PROPERTIES.registerPath, params, { headers: PROPERTIES.JsonValue });
        },

		login : function (email, password /*,remember*/) {
			var params = {
				email : email,
                password : password,
				//remember : remember
			};
			return $http.post(PROPERTIES.authenticationPath, params, {headers: PROPERTIES.JsonValue});
		},

        logout : function () {
            $rootScope.currentUser = {};
            $rootScope.loggedIn = false;
            $location.path("/login");
        }
	}
})


