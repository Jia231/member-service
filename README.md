## Demo Project

### Swagger
1. implementation 'io.springfox:springfox-swagger2:2.9.2'
2. implementation 'io.springfox:springfox-swagger-ui:2.9.2'
3. SwaggerConfig


### Security
1. implementation 'org.springframework.boot:spring-boot-starter-security' 
2. Security config 

### Liquibase
1. implementation 'org.liquibase:liquibase-core:3.8.0'
2. create in resources folder db/changelog and add db.changelog-master.yaml


### Docker 
FROM openjdk:11

RUN adduser --system --group spring

USER spring:spring

ARG JAR_FILE=build/libs/*.jar

EXPOSE 7000

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]


Look into jar contents "jar tf build/libs/demo-0.0.1-SNAPSHOT.jar"


### Database integration Test
1. Add h2 dependency
2. Add test properties
3. Add Database integration test


### Oauth2
1. Add Oauth dependency
2. Add id and secret
3. Add configuration in WebSecurityConfigurerAdapter
4. In Oauth application add URI "http://localhost:'port'/login/oauth2/code/google"

