# Spring Boot + Kotlin + WebFlux auth implementation

## How to

### SpringBoot + Kotlin + Webflux basic spring security

Super simple authentication. The default username is `user` and password is in the application log eg.: `Using generated security password: 6016bafb-6d87-4780-ae18-e93fac256ffe
`

#### Git commit

d468ef86ff3149d952b2a31cc4400a83fbbd7a78

#### Run

`gradle bootRun`

#### Test

Secured endpoint:\
http://localhost:8080/api/private

Public endpoint:\
http://localhost:8080/api/public

Spring boot login/logout:\
http://localhost:8080/login \
http://localhost:8080/login