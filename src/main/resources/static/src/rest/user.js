angular.module("adExtreme")
.factory("User", function UserFactory($http,PROPERTIES, $location, $rootScope) {
	return {
        getBalance : function (userId) {
            $mask();
            return $http.get(PROPERTIES.authenticatedRestPath + "balance/" + userId);
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
            console.log("Logout :" + $rootScope.currentUser.name);
            $rootScope.currentUser = {};
            $rootScope.loggedIn = false;
            $location.path("/login");
        }
	}
})


