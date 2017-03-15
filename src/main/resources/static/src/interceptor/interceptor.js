/**
 * Created by nicacio on 14/03/17.
 */
angular.module("adExtreme")
.factory("Interceptor", function ($q, $location, PROPERTIES) {
    return {

        'request': function (config) {
            config.headers.Authorization = 'Bearer ' + localStorage.getItem("adExtremeUserToken");
            return config;
        },
        
        'responseError': function (rejection) {
            if (rejection.status == PROPERTIES.unauthorized ) {
                $location.path(PROPERTIES.loginPath);

            }
            return $q.reject(rejection);
        }

        

    };
});