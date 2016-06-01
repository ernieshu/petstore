'use strict';

describe('Controller: DeleteCtrl', function () {

  // load the controller's module
  beforeEach(module('petstoreUiApp'));

  var DeleteCtrl,
    scope, httpBackend, http;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $httpBackend, $http) {
    scope = $rootScope;
    httpBackend = $httpBackend;

    // valid response
    httpBackend.when("DELETE", "/pet/1").respond([200]);
    // invalid response
    httpBackend.when("DELETE", "/pet/-1").respond(function(method, url, data, headers, params) {
      return [404];
    });

    DeleteCtrl = $controller('DeleteCtrl', {
      '$scope': $rootScope
    });
  }));

  afterEach(function() {
    httpBackend.verifyNoOutstandingExpectation();
    httpBackend.verifyNoOutstandingRequest();
  });

 it('delete controller should make a http delete call and be successful for an existing pet', function () {

      DeleteCtrl.deletePetId = 1;
      DeleteCtrl.deleteAPet();
      httpBackend.expectDELETE('pet/1').respond([200]);
      httpBackend.flush();
  });


  it('should return 404 when the invalid pet is sent back', function () {
      DeleteCtrl.deletePetId = -1;
      DeleteCtrl.deleteAPet();
      httpBackend.expectDELETE('pet/-1').respond([404]);
      httpBackend.flush();
  });  

});
