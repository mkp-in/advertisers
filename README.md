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

    
    Type   | Method | URL       | Returns
    -------|--------|-----------|--------
    Get advertiser| GET| http://localhost:8080/api/advertiser/get?id=2| 200
    Delete advertiser| DELETE | http://localhost:8080/api/advertiser/delete?id=2 | 200
    Post new advertiser |POST | http://localhost:8080/api/advertiser/insert | 204
    Put update advertiser | PUT | http://localhost:8080/api/advertiser/update?id=3 | 204
    Get to check credit limit for transaction| GET | http://localhost:8080/api/advertiser/checkLimit?id=1&requested=5000 | 200
    
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

On the command line type in:
    
    > mvn clean package jacoco:report   # this command will build the jar and produce the Jacoco report
    > java -jar target/springbootlearning-0.SNAPSHOT.jar # this command will run the application
    
### Jacoco Report

These are located at:
 
    target/site/jacoco/index.html
    
### Actuator

Running on port 8090

 

    
  
     



 


