'use strict';

describe('Controller: ViewCtrl', function () {

  // load the controller's module
  beforeEach(module('petstoreUiApp'));

  var ViewCtrl,
    scope, httpBackend, http;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $httpBackend, $http) {

    scope = $rootScope;

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

      var mockPet = {
        "id": 1,
        "name": "pet name",
        "category": {
            "id": 1,
            "name": "Category 1"
        },
        "photoUrls": [
            "sadfasfdx",
            "sdfasdf"
        ],
        "tags": [
            {
                "id": 0,
                "name": "Tags 0"
            },
            {
                "id": 1,
                "name": "Tags 1"
            }
        ],
        "status": "AVAILABLE"
      }
      ViewCtrl.searchPetId = 1;
      ViewCtrl.findAPet();
      httpBackend.expectGET('pet/1').respond([200, mockPet, {}]);
      // check to see the expected pet on the scope
      expect(scope.viewPet).not.toBe(null);
      // expect(scope.viewPet.id).not.toBe(null);
      // expect(scope.viewPet.id).toBe(1);
      // expect(scope.viewPet.name).toBe('mockPet');
      httpBackend.flush();
  });


  it('should return 404 when the invalid pet is sent back', function () {
      ViewCtrl.searchPetId = -1;
      ViewCtrl.findAPet();
      httpBackend.expectGET('pet/-1').respond([404]);
      expect(scope.viewPet).toBeUndefined();
      httpBackend.flush();
  });  
});
