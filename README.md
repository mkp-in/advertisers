# Advertisers
This project contains Restful API for the Advertiser Domain Model

### Technologies used:

* SprintBoot 2 (WEB)
* MyBatis
* H2
* JUnit
* Actuator
* Flyway
* Maven
* Git
* Jacoco
* Docker
* Swagger -> Had issue with getting Swagger UI working with SpringBoot2. Did not want to spend too much time trying to figure it out, but some thread on Swagger issues page showed that it was a known issue. For now, I'll document the API here.
___
### API

    
    Type                      | Method | URL                                                                 | Returns
    ------------------------- |--------|---------------------------------------------------------------------|--------::
    Get advertiser            | GET    | http://localhost:8080/api/advertiser/get?id=2                       | 200
    Delete advertiser         | DELETE | http://localhost:8080/api/advertiser/delete?id=2                    | 200
    Post new advertiser       | POST   | http://localhost:8080/api/advertiser/insert                         | 204
    Put update advertiser     | PUT    | http://localhost:8080/api/advertiser/update?id=3                    | 204
    Get to check credit limit | GET    | http://localhost:8080/api/advertiser/checkLimit?id=1&requested=5000 | 200
    
Example JSON for using POST:

    {
      "name":"AdvertiserD",
      "contactName":"Advertiser D Contact",
      "creditLimit":9000
    }
    
Example JSON for PUT

    {
      "name":"AdvertiserD",
      "contactName":"Advertiser D Contact",
      "creditLimit":999000
    }
    
### Running the code:

There are two ways to run the project:

* **Method 1**

    
    $ mvn clean package    # this command will build the jar
     
    $ java -jar target/springbootlearning-0.SNAPSHOT.jar # this command will run the application

* **Method 2**

    
    $ mvn clean spring-boot:run


### Jacoco Report

To generate the Jacoco code coverage reports, use the following command:

    $ mvn clean package jacoco:report   # this command will build the jar and produce the Jacoco report

Location of generated reports:
 
    target/site/jacoco/index.html
    
### Actuator

Running on port 8090
   
    http://localhost:8090/mgmt/

### Docker

Before you can run docker, alter the repository section in pom.xml to specify your repository.

                    <configuration>
                        <repository>......</repository>
                        <tag>${project.version}</tag>
                        <buildArgs>
                            <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                        </buildArgs>
                    </configuration>


Run these steps to run the project using docker:

* Build the image using the following command:


    $  mvn clean install dockerfile:build

The above command will build the image to run the docker container. If you are running this the first time, it will take a bit longer since the different layers to build the images are being downloaded. Subsequent runs should be lot faster.

* Verify the docker image is created:


    $ docker images | grep <REPOSITORY_NAME>

Replace <REPOSITORY_NAME> by what you put in the pom.xml above. 

* Run the docker container:


     $ docker run -d -p 5000:8080 <REPOSITORY_NAME>:0.SNAPSHOT
    
The above command will run docker on port 5000 mapped to 8080 inside the container. You can query the container is running by hitting: http://localhost:5000/api/advertiser/get?id=3

The above command will also return a container id. You can use that id to kill the running container using the command:


    $ docker kill <CONTAINER_ID>
     


    
### Logging

Logs are written to logs/ directory    

 
### JAVA 9/10 ERROR

If you are running Java version above 8, you might get the following error:

    [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.7.0:compile (default-compile) on project advertisers: Fatal error compiling: java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTags -> [Help 1]
    
To fix the above, set JAVA_HOME to appropriate version.    
    
  
     



 


