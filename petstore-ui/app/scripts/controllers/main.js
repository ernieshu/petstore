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

    $scope.types = [
      'Type 0', 
      'Type 1',
      'Type 2', 
      'Type 3'
    ];

    $scope.addAPet = function() {
    	console.log("adding a pet with name:" + $scope.name);

    	var petToBeAdded = {
    		name : $scope.name,
        category : {
          id: $scope.categoryId
        },
        status : $scope.status.toUpperCase()

        // TODO - input for TAGS
        // TODO - input for PhotoURLs
    	};
    	$http.post('pet', petToBeAdded).success(function(data) {
	  		// if we've gotten the pet, map it back to the GUI
	  		$scope.petName = data.name;
	  	});
    };

    this.findAPet = function(data) {
	  	$http.get('pet/' + data.petId).success(function(data) {
	  		// if we've gotten the pet, map it back to the GUI
	  		$scope.petName = data.name;
	  	});
    };
  });
