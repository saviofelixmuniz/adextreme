var HOME_PAGE = 'templates/pages/home/home.html';
var LOGIN_PAGE = 'templates/pages/login/login.html';
var REGISTER_PAGE = 'templates/pages/register/register.html';
var AD_REGISTER_PAGE = 'templates/pages/ad-register/ad-register.html';
var AD_PAGE = 'templates/pages/ad/ad.html';
var USER_HOME_PAGE = 'templates/pages/user-home/user-home.html';
var RANKING_PAGE = 'templates/pages/ranking/ranking.html'

var RANKING_CONTROLLER = 'RankingCtrl'
var USER_HOME_CONTROLLER = "UserHomeCtrl"
var LOGIN_CONTROLLER = "LoginCtrl";
var REGISTER_CONTROLLER = "RegisterCtrl";
var AD_REGISTER_CONTROLLER = "AdRegisterCtrl";
var AD_CONTROLLER = "AdCtrl";

var FREE_PAGES = [HOME_PAGE, REGISTER_PAGE, LOGIN_PAGE];

angular.module('adExtreme')
.config(function($routeProvider) {
	$routeProvider
	.when('/', {
		redirectTo:'home'
	})
	.when('/home', {
		templateUrl:HOME_PAGE
	})
	.when('/login', {
		templateUrl:LOGIN_PAGE,
		controller: LOGIN_CONTROLLER
	})
	.when('/register', {
		templateUrl: REGISTER_PAGE,
		controller: REGISTER_CONTROLLER
	})
	.when('/ranking', {
		templateUrl: RANKING_PAGE,
		controller: RANKING_CONTROLLER
	})
	.when('/ad-register', {
		templateUrl: AD_REGISTER_PAGE,
		controller: AD_REGISTER_CONTROLLER
	})
	.when('/user-home', {
		templateUrl: USER_HOME_PAGE,
		controller: USER_HOME_CONTROLLER
	})
	.when('/ad', {
		templateUrl: AD_PAGE,
		controller: AD_CONTROLLER
	})
	.otherwise( {
		redirectTo: 'home'
	})
})
.run(function($rootScope, $location) {
    $rootScope.$on("$routeChangeStart", function(event, next, current) {
	    if (!$rootScope.loggedIn) {
    		console.log("routeChanged");
    		console.log(next);
	        if ($.inArray(next.templateUrl, FREE_PAGES) == -1) {
	            $location.path("/login");
	        }
        } 
    });
})
	// interceptor
.config(function ($httpProvider) {
    $httpProvider.interceptors.push("Interceptor");

});