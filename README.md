# Spring Boot File Upload & Download Rest API
----------------------------------

**GET  : localhost:8080**
* open postman and make a get request with this url, you will get response string "Home Page"

**POST : localhost:8080/upload**
* open postman and make a get request with this url, you will get response string "Home Page"

**GET  : localhost:8080/upload**
* make a post request, in the body section select file which you want to upload

**GET  : localhost:8080/upload/{id}**
* make a get request and you will information of all the file that uploaded

**GET  : localhost:8080/download/{id}**
* make a get request and with any valid id of the uploaded file, you will get only the details of that file
open google chrome and make a get request with the id of any uploaded file, the fill will start download automatically

**GET  : localhost:8080/search/{filename}**
* make a get request with the file name you uploaded(without the file extension), you will get the id of the file


_Scheduler is set to 10 min initially, keep the app running and you will get mail with the uploaded file info in every 10 min_

_As all the file info is in in-memory db once you stop the app all the db information will be erased_

Enjoy :tada:
