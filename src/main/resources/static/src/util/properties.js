"use strict"

var REST_PATH = '/user';
var AUTHENTICATED_REST_PATH = '/user/';

var AD_PATH = '/ads/';
var PURCHASE_PATH = '/purchase/';
var LOGIN_PATH = '/login/';
var REGISTER_PATH = '/register/';
var AUTHENTICATION_PATH = '/authenticate/';
var USERS_PATH = '/users/';

// ENUM errors

var UNAUTHORIZED = 401;

angular.module("adExtreme")
.constant("PROPERTIES", {

	//Paths
	userPath : USERS_PATH,
	authenticationPath : AUTHENTICATION_PATH,
	registerPath : REGISTER_PATH,
	purchasePath : PURCHASE_PATH,
	loginPath : LOGIN_PATH,
	restPath: REST_PATH,
	authenticatedRestPath : AUTHENTICATED_REST_PATH,
	adPath : AD_PATH,

	//ENUM errors
	unauthorized : UNAUTHORIZED,

	// Headers configs
	wwwForm: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
	JsonValue: {'Content-Type':['application/json']}
});