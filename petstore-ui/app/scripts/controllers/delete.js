'use strict';
angular.module('petstoreUiApp')
  .controller('DeleteCtrl', function ($scope, $http) {
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
