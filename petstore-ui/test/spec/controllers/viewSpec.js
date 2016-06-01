'use strict';

describe('Controller: ViewCtrl', function () {

  // load the controller's module
  beforeEach(module('petstoreUiApp'));

  var ViewCtrl,
    scope, httpBackend, http;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $httpBackend, $http) {

    httpBackend = $httpBackend;

    // valid response
    // httpBackend.when("GET", "/pet/1").respond([{}, {}, {}]);
    httpBackend.when("GET", "/pet/1").respond([200]);
    // invalid response
    httpBackend.when("GET", "/pet/-1").respond(function(method, url, data, headers, params) {
      return [404];
    });

    ViewCtrl = $controller('ViewCtrl', {
      '$scope': $rootScope
    });
  }));

  afterEach(function() {
    httpBackend.verifyNoOutstandingExpectation();
    httpBackend.verifyNoOutstandingRequest();
  });

  it('view controller should make a http get call and be successful', function () {
      ViewCtrl.searchPetId = 1;
      ViewCtrl.findAPet();
      // TODO augment the test below to also return a dummy pet
      httpBackend.expectGET('pet/1').respond([200]);
      // TODO check to see the expected pet on the scope
      httpBackend.flush();
  });


  it('should return 404 when the invalid pet is sent back', function () {
      ViewCtrl.searchPetId = -1;
      ViewCtrl.findAPet();
      // TODO augment the test below to also return a dummy pet
      httpBackend.expectGET('pet/-1').respond([404]);
      // TODO check to see the expected pet on the scope
      httpBackend.flush();
  });  
});
