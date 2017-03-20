angular.module("adExtreme")
    .factory("Advertisement", function AdFactory($http, PROPERTIES) {
        return {
            handleTransaction : function (buyerId, adId) {
                var params = {
                    buyerId : buyerId,
                    adId : adId
                };

                return $http.post(PROPERTIES.authenticatedRestPath + PROPERTIES.adPath + "/purchase", 
                                  params, {headers : PROPERTIES.JsonValue});
            },

            getByUser: function (userId) {
                return $http.get(PROPERTIES.authenticatedRestPath + PROPERTIES.adPath + "/" + userId);
            },

            getAll : function () {
                return $http.get(PROPERTIES.authenticatedRestPath + PROPERTIES.adPath + "/all");
            },

            getTransactions : function(userId) {
                return $http.get(PROPERTIES.authenticatedRestPath + PROPERTIES.adPath + "/transactions/" + userId);
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