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

    $scope.categories = [
      { id: 0, name: 'Category 0'},
      { id: 1, name: 'Category 1'},
      { id: 2, name: 'Category 2'},
      { id: 3, name: 'Category 3'}
    ];

    $scope.tags = [
      { id: 0, name: 'Tag 0'},
      { id: 1, name: 'Tag 1'},
      { id: 2, name: 'Tag 2'},
      { id: 3, name: 'Tag 3'}
    ];

    $scope.pet = {
      tags: []
    };

    $scope.addAPet = function() {
    	console.log('adding a pet with name:' + $scope.name);

    	var petToBeAdded = {
    		name : $scope.name,
        category : {
          id: $scope.pet.categoryId
        },
        status : $scope.status.toUpperCase(),
        photoUrls: [
          $scope.pet.photoUrl
        ],
        tags: $scope.pet.tags
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
