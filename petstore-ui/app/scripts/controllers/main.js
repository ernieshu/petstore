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
      tags: [],
      photoUrls: []
    };

    $scope.addAPet = function() {

      // pre-process the photoUrls, so that only strings are passed, rather than full objects
      var photoUrlStrings = $scope.pet.photoUrls.map(function(photoUrl) { return photoUrl.text; });
    	var petToBeAdded = {
    		name : $scope.name,
        category : {
          id: $scope.pet.categoryId
        },
        status : $scope.status.toUpperCase(),
        photoUrls: photoUrlStrings,
        tags: $scope.pet.tags
        // TODO - input for PhotoURLs
    	};
    	$http.post('pet', petToBeAdded).success(function(data) {
	  	});
    };

    this.findAPet = function(data) {
	  	$http.get('pet/' + data.petId).success(function(data) {
	  		// if we've gotten the pet, map it back to the GUI
	  		$scope.petName = data.name;
	  	});
    };
  });
