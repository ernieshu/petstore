'use strict';
angular.module('petstoreUiApp')
  .controller('ViewCtrl', function ($scope, $http) {

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
          $scope.feedbackMessage = null;
        })
        .error(function(data, status, headers, config){
          if (status===404) {
            $scope.feedbackMessage = 'No pet found';
            $scope.viewPet = null;
          }
          else {
            $scope.feedbackMessage = 'System error';
            $scope.viewPet = null;
          }
        });
    };
  });
