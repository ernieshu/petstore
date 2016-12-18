# Implementation Notes

* For tags and categories, made FK reference tables

* For data model, used a model with FKs, with the following mappings

    * Pet --> Category - 1:1, with Categories as a static list
    * Pet --> Tag - Many: Many, with Tags as a static list
    * Pet --> PhotoURLs - 1:Many
    * Pet --> Status - Enum

## Controller Layer
* For create interface, did validation
     * If invalid, return a 'bad request (HTTP 400)' rather than the specified 405

* Note that there's a transformation layer, between UI / backend tier Transfer Object, and backend Domain Object
	* used JPA to resolve transformations
	
# How to build and run
./buildAndRunMe.sh

# TODO
Find below a list of items that would make this implementation better / more complete.
* Completion of Integration tests
  * Automated RESTful tests, that run against the API tier, with an in-memory DB running against said backend
  * In-memory DB should contain a known data set, to support positive and negative integration tests
* Maven release configuration.
* Migration to a proper backend DB, including:
  * usage of Flyway / Liquibase