'use strict';

describe('Controller: CreateCtrl', function () {

  // load the controller's module
  beforeEach(module('petstoreUiApp'));

  var CreateCtrl,
    scope, httpBackend, http;

  var validInputPet = {
    'name': 'validPet',
    'status' : 'Available',
    'categoryId' : '1',
    'tags' : [
      {
        'id': 0, 
        'name': 'Tag 0'
      }
    ],
    'photoUrls' : [
      {
        'id' : 1,
        'text' : 'AbcdText'
      }
    ]
  };
  // this copy of the pet will have the photoUrls rewritten, to avoid the id / text key split
  var validPetSentToServerSide = {
    'name': 'validPet',
    'status' : 'AVAILABLE',
    'category' : {
      'id' : '1',
    },
    'tags' : [
      {
        'id': 0, 
        'name': 'Tag 0'
      }
    ],
    'photoUrls' : [
       'AbcdText'
    ]
  };

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $httpBackend, $http) {
    scope = $rootScope;
    httpBackend = $httpBackend;

    // valid pet to be created
    httpBackend.whenPOST("/pet/1",validInputPet).respond([200]);

    CreateCtrl = $controller('CreateCtrl', {
      '$scope': $rootScope
    });

  }));

  afterEach(function() {
    httpBackend.verifyNoOutstandingExpectation();
    httpBackend.verifyNoOutstandingRequest();
  });

  it('When sent a properly formed pet, should return HTTP 200', function () {
    CreateCtrl.pet = validInputPet;
    CreateCtrl.addAPet();

    // assert that the right object is set to the server side
    // also asserts that the photoURL strings are properly formatted
    httpBackend.expectPOST('pet', validPetSentToServerSide).respond([200]);
    httpBackend.flush();

  });
});
