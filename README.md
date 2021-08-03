어디야
<div align="center">
<img width="228" alt="logo" src="https://user-images.githubusercontent.com/63579113/119791189-8d3e0a00-bf0f-11eb-943c-be697b653ce1.png">
</div>


 ## Service Description 
 **코레안 타임을 없애기 위한 서비스입니다**. 

 ## Using Tech
 - Spring Boot(Spring Security, JWT, JPA)
 - MySQLDB
 - AWS(EC2,RDS,S3) 
 - WebSocket 

## ERD 
<img width="695" alt="erd" src="https://user-images.githubusercontent.com/63579113/120812992-7428ff00-c588-11eb-9179-de157338a685.png">

 ## API WIKI 
- [API 문서](https://github.com/Yboyu0u/WhereYouAt_API/wiki)

## Service Architecture 
![image](https://user-images.githubusercontent.com/63579113/121895281-01bfd800-cd5b-11eb-8501-6ce0a479f47a.png)

## Dependency
```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-websocket'
	implementation 'org.springframework.boot:spring-boot-starter-web-services'
 	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	implementation 'com.h2database:h2'
	compile 'org.springframework.boot:spring-boot-starter-jdbc'
	compile('javax.servlet:jstl')
	compile 'mysql:mysql-connector-java'
	compile('org.webjars.bower:jquery:3.3.1')
	compile('org.webjars:sockjs-client:1.1.2')
	compile('org.webjars:webjars-locator:0.30')
	implementation 'org.projectlombok:lombok:1.18.16'
	implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
	runtime 'io.jsonwebtoken:jjwt-impl:0.11.2'
	runtime 'io.jsonwebtoken:jjwt-jackson:0.11.2'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.junit.platform:junit-platform-launcher:1.5.0'
	testImplementation 'org.junit.jupiter:junit-jupiter-api:5.5.0'
	testImplementation 'org.junit.jupiter:junit-jupiter-engine:5.5.0'
	testImplementation 'org.mockito:mockito-junit-jupiter'
	implementation group: 'org.springframework.cloud', name: 'spring-cloud-starter-aws', version: '2.2.1.RELEASE'
}

```
