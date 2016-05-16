'use strict';

/**
 * @ngdoc function
 * @name petstoreUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the petstoreUiApp
 */
angular.module('petstoreUiApp')
  .controller('MainCtrl', function ($scope, $http) {

  	console.log("Starting a console log of this working...!");

    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.addAPet = function() {
    	console.log("adding a pet with name:" + $scope.name);

    	// $http.post('pet');
    };

    this.findAPet = function(data) {
	  	$http.get('pet/' + data.petId).success(function(data) {
	  		// if we've gotten the pet, map it back to the GUI
	  		$scope.petName = data.name;
	  	});
    };
  });
