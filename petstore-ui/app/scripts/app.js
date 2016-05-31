'use strict';

/**
 * @ngdoc overview
 * @name petstoreUiApp
 * @description
 * # petstoreUiApp
 *
 * Main module of the application.
 */
angular
  .module('petstoreUiApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'checklist-model',
    'ngTagsInput',
    'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/view', {
        templateUrl: 'views/view.html',
        controller: 'ViewCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
