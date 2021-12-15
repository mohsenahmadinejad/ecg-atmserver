# ATM Code Challenge

This is a Java based project using Maven for building and managing.

# Tech stack
1. `Java 8`
2. `Spring boot + Security`
3. `Maven`
4. `Docker`
6. `Swagger`
7. `Junit`

# Project's structure

- src/main/java - Server side java + spring based implementation
- src/test/java - Server side java + spring based Junit test case

 
 # How to run
 
 # 1.Build 
You can build and run it into any Development Idea
Otherwise, this is a step-by-step guide on how to build and run the Code Challenge:
 
- Start a terminal, then run:
- mvn clean install (be sure JAVA_HOME is set to a Java 8 version home directory)
  (This will take a while to download the dependencies and 
 build flixin-code-challenge jar file. It will successfully build atm-0.0.1-SNAPSHOT.jar file in target directory)

Besides, you can run the project by using Docker
- docker-compose -f docker-compose.yml build
- docker-compose -f docker-compose.yml up -d

 # 2.Run 
 - open http://localhost:8080
 - default user role's username/password is: user1/pwd1 

-- for disable security go to com.example.atmserver.demo.security.config.SecurityConfig.java
    and uncomment code in method "public void configure(WebSecurity web) throws Exception"

   
# ecg-atmserver
