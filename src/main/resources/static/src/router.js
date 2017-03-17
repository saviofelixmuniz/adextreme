var HOME_PAGE = 'templates/pages/home/home.html';
var LOGIN_PAGE = 'templates/pages/login/login.html';
var REGISTER_PAGE = 'templates/pages/register/register.html';
var AD_REGISTER_PAGE = 'templates/pages/ad-register/ad-register.html';
var AD_PAGE = 'templates/pages/ad/ad.html';
var USER_HOME_PAGE = 'templates/pages/user-home/user-home.html'

var USER_HOME_CONTROLLER = "UserHomeCtrl"
var LOGIN_CONTROLLER = "LoginCtrl";
var REGISTER_CONTROLLER = "RegisterCtrl";
var AD_REGISTER_CONTROLLER = "AdRegisterCtrl";
var AD_CONTROLLER = "AdCtrl";

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

	// interceptor
.config(function ($httpProvider) {
    $httpProvider.interceptors.push("Interceptor");

});