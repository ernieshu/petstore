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
    };

    $scope.addAPet = function() {

      // pre-process the photoUrls, so that only strings are passed, rather than full objects
      var photoUrlStrings = [];
      if ($scope.pet.photoUrls!=null) {
        photoUrlStrings = $scope.pet.photoUrls.map(function(photoUrl) { return photoUrl.text; });
      }

    	var petToBeAdded = {
    		name : $scope.name
    	};

      // only populate certain items if they've been entered
      if ($scope.pet.status!=null) {
        petToBeAdded["status"] = $scope.pet.status.toUpperCase();
      }
      if ($scope.pet.categoryId!=null) {
        petToBeAdded["category"] = { id: $scope.pet.categoryId };
      }
      if ($scope.pet.tags!=null) {
        petToBeAdded["tags"] = $scope.pet.tags;
      }
      if (photoUrlStrings!=null) {
        petToBeAdded["photoUrls"] = photoUrlStrings;
      }

    	$http.post('pet', petToBeAdded).success(function(data) {
        console.log('Entity created with id:' + data.id);
	  	});
    };

    this.findAPet = function(data) {
	  	$http.get('pet/' + data.petId).success(function(data) {
	  		// if we've gotten the pet, map it back to the GUI
	  		$scope.petName = data.name;
	  	});
    };
  });
