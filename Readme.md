This is a test task to implement Archiver service which will accept
a list of files via `form-data` http request and return them archived.  

[OpenApi Specification](openapi.yaml)  
Service UI [http://localhost:8080](http://localhost:8080)  
Alpha tests reports: [Cucumber](features/build/reports/tests/alphaTest/alpha-report.html), [JUnit](features/build/reports/tests/alphaTest/index.html).  
See [What is Alpha, Beta, and Gamma testing](https://spectsteps.substack.com/p/difference-between-alpha-testing)

## Prerequisites
- Java 17 
- Docker

## Build and Run

Local run
```shell
make build
make run
```

Dockerized run  
```shell
make buildDocker
make runDocker
```

## Test

Alpha tests run, as cucumber tests and local app in docker.  
This task will take longer to run first time.
```shell
make alphaDocker
```

Standalone run from IDE is also possible for both Alpha tests and app.  

Run tests against service running locally
```shell
make run
make alpha
```

## Configuration
Please see app specific configuration options in [application.properties](src/main/resources/application.properties) file.
