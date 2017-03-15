angular.module("adExtreme")
    .factory("Advertisement", function AdFactory($http, PROPERTIES) {
        return {
            getAll : function () {
                return $http.get(PROPERTIES.authenticatedRestPath + PROPERTIES.adPath);
            },

            register : function(title, price, type, idOwner, owner) {
                var params = {
                    title : title,
                    price : price,
                    type : type,
                    idOwner : idOwner,
                    owner : owner
                };
                return $http.post(PROPERTIES.authenticatedRestPath + PROPERTIES.adPath, params, {headers: PROPERTIES.JsonValue});
            }

        }
    });