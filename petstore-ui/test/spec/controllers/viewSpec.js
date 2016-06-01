'use strict';

describe('Controller: ViewCtrl', function () {

  // load the controller's module
  beforeEach(module('petstoreUiApp'));

  var ViewCtrl,
    scope, httpBackend;



  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $httpBackend, $http) {
    scope = $rootScope.$new();
    httpBackend = $httpBackend;
    // valid response
    httpBackend.when("GET", "/pet/1").respond([{}, {}, {}]);
    // invalid response
    httpBackend.when("GET", "/pet/-1").respond(function(method, url, data, headers, params) {
    return [404];
  }));
    ViewCtrl = $controller('ViewCtrl', {
      $scope: scope,
      $http: http
    });
  }));

  it('should return a valid set of pet ', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });

  it('should return 404 when the invalid pet is sent back', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });  
});
