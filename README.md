# Petstore # 

## How to build ##

### petstore-services ###
* mvn clean package
### petstore-ui ###
* npm install
* bower install
* grunt build

## How to run ##

### Running the backend API ###
* java -jar target/petstore-services.jar
OR 
* run com.iwd.petstore_serivces.AppController as a Java Application in Eclipse

### Running the frontend ###
* grunt serve