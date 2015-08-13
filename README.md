# To Run:
    
    mvn spring-boot:run
    
## To run with externalized properties:
    
    mvn spring-boot:run -Dspring.config.location={path to your property directory, including trailing '/'}
    
## To build docker container


    mvn package 
    
Set environment variable DOCKER_HOST=tcp://hostname:2375 to run against a remote Docker host. Otherwise, will use localhost
