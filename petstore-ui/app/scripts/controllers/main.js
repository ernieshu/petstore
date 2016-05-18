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
      { id: 0, name: 'Type 0'},
      { id: 1, name: 'Type 1'},
      { id: 2, name: 'Type 2'},
      { id: 3, name: 'Type 3'}
    ];

    $scope.pet = {
      types: []
    };

    $scope.addAPet = function() {
    	console.log("adding a pet with name:" + $scope.name);

    	var petToBeAdded = {
    		name : $scope.name,
        category : {
          id: $scope.categoryId
        },
        status : $scope.status.toUpperCase(),
        photoUrls: [
          "photoUrlString"
        ],
        tags: $scope.pet.types
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
