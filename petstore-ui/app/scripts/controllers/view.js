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
