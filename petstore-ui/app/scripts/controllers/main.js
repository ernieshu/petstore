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

    this.categories = [
      { id: 0, name: 'Category 0'},
      { id: 1, name: 'Category 1'},
      { id: 3, name: 'Category 3'}
    ];

    this.tags = [
      { id: 0, name: 'Tag 0'},
      { id: 1, name: 'Tag 1'},
      { id: 2, name: 'Tag 2'},
      { id: 3, name: 'Tag 3'}
    ];

    this.pet = {
    };

    $scope.viewPet = {
    };

    this.addAPet = function() {

      // pre-process the photoUrls, so that only strings are passed, rather than full objects
      var photoUrlStrings = [];
      if (this.pet.photoUrls!==null) {
        photoUrlStrings = this.pet.photoUrls.map(function(photoUrl) { return photoUrl.text; });
      }

    	var petToBeAdded = {
    		name : this.pet.name
    	};

      // only populate certain items if they've been entered
      if (this.pet.status!==null) {
        petToBeAdded['status'] = this.pet.status.toUpperCase();
      }
      if (this.pet.categoryId!==null) {
        petToBeAdded['category'] = { id: this.pet.categoryId };
      }
      if (this.pet.tags!==null) {
        petToBeAdded['tags'] = this.pet.tags;
      }
      if (photoUrlStrings!==null) {
        petToBeAdded['photoUrls'] = photoUrlStrings;
      }

    	$http.post('pet', petToBeAdded)
        .success(function(data) {
          console.log('Entity created with id:' + data.id);
	  	  })        
        .error(function(data, status, headers, config){
          if (status=='404') {
            console.log("no pet found");
          }
          else if (status='500') {
            console.log("system error");
          }
        });
    };

    this.findAPet = function(data) {
	  	$http.get('pet/' + this.searchPetId)
        .success(function(data, status, headers, config) {
          console.log("Retrieving info for pet name: " + data.name);
  	  		// if we've gotten the pet, map it back to the GUI
          $scope.viewPet = {
            id: data.id,
            name: data.name,
            category: data.category,
            status: data.status,
            tags: data.tags,
            photoUrls: data.photoUrls
          };
  	  	})
        .error(function(data, status, headers, config){
          if (status=='404') {
            console.log("no pet found");
          }
          else if (status='500') {
            console.log("system error");
          }
        });
    };

    this.deleteAPet = function(data) {
      $http.delete('pet/' + this.deletePetId)
        .success( function(data) {
          console.log("Successfully deleted pet");
        })
        .error(function(data, status, headers, config){
          if (status=='404') {
            console.log("no pet found");
          }
          else if (status='500') {
            console.log("system error");
          }
        });
    };
  });
