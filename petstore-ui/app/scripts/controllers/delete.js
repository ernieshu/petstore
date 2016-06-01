'use strict';
angular.module('petstoreUiApp')
  .controller('DeleteCtrl', function ($scope, $http) {
    this.deleteAPet = function(data) {
      $http.delete('pet/' + this.deletePetId)
        .success( function(data) {
          $scope.feedbackMessage = 'Successfully deleted pet';
        })
        .error(function(data, status, headers, config){
          if (status===404) {
            $scope.feedbackMessage = 'No pet found';
          }
          else {
            $scope.feedbackMessage = 'System error';
          }
        });
    };
  });
