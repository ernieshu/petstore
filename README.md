README.md

h1. Petstore 

h2. How to build

* in petstore-services
** mvn clean package
* in petstore-ui
** grunt build

h2. How to run

h3. Running the backend API
** java -jar target/petstore-services.jar
OR 
** run com.iwd.petstore_serivces.AppController as a Java Application in Eclipse

h3. Running the frontend
** grunt serve
