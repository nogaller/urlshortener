# URL Shortener Service
This project contains a simple URL Shortener service which exposes its functionality via a Web Service API.  Comprarable to GOO.GL shortener.

## Assignment
The implementation of this service is not quite complete. Implement the following features:

- ☑ A user can add a website URL for which a short URL is created and stored in the database. The short URL must have a unique ID
  consisting of alphanumeric characters.
- ☑ The service exposes a Web Service API for providing appropriate CRUD operations to consumers.
- ☑ *Optional*: The Web Service API accepts and provides data in multiple formats, e.g. JSON, XML, etc.
- ☑ When opening a short URL in a Web Browser the website of the original URL shows up.
- ☐ use MurMur3 or comparable easy memorizable Hash for short links
- ☐ Whenever someone opens a short URL a set of statistics is stored (number of calls, date and time, user agent, referrer, etc.).
- ☐ These statistics can be retrieved via the Web Service API.


## Work with the Project

### Build
This project uses [Maven](https://maven.apache.org) as its build tool. In order to build, test and package the application
run the following command:

```bash
mvn clean package
```

## Run
Once the application has been build and packaged successfully, you'll find a JAR file in the `target` folder. The file
is called `url-shortener.jar`. Use the following command to run the application:

```bash
java -jar target/url-shortener.jar
```

After a few seconds the application should have started successfully. You can access the Web Service endpoints via
[http://localhost:8080](http://localhost:8080) now.

**Usage examples:**

[http://localhost:8080/?url=https://www.my.examle.com/test?param=x](http://localhost:8080/?url=https://www.my.examle.com/test?param=x) - will create a new shortened URL

[http://localhost:8080/748e48c3](http://localhost:8080/748e48c3) - resulting URL, will open original website on click

[http://localhost:8080/748e48c3?url=http://somewhere.else/](http://localhost:8080/748e48c3?url=http://somewhere.else/) - relink URL, somewhere else

[http://localhost:8080/list](http://localhost:8080/list) - list all saved shortcuts

[http://localhost:8080/list](http://localhost:8080/list?filter=example.com) - filtered result list ignoring case, works for XML/JSON as well

[http://localhost:8080/list/json](http://localhost:8080/list/json) - list all saved shortcuts as **JSON**

[http://localhost:8080/list/json](http://localhost:8080/list/xml) - list all saved shortcuts as **XML**

To delete entry use **curl** from command line

```bash
curl -X DELETE http://localhost:8080/748e48c3
```

**Documentation**

API JSON [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

Swagger [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
