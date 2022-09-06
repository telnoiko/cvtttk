This is a simple service that archives files that were sent to it. It accepts
a list of files via `form-data` http request and returns compressed archive.  

Test UI available at [https://ko7ko-archiver.herokuapp.com](https://ko7ko-archiver.herokuapp.com).  

[OpenApi Specification](openapi.yaml).  

## Prerequisites
- Java 17 
- Docker

## Build and Run

Local run, access test UI at [http://localhost:8080](http://localhost:8080).
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
This task will take longer to run the first time.
```shell
make alphaDocker
```

Standalone run from IDE is also possible for both Alpha tests and app.  

Run tests against service both running locally.  
Reports: [alpha cucumber](features/build/reports/tests/alphaTest/alpha-report.html), [alpha JUnit](features/build/reports/tests/alphaTest/index.html).  
See [What is Alpha, Beta, and Gamma testing](https://spectsteps.substack.com/p/difference-between-alpha-testing).  
```shell
make run
make alpha
```

## Configuration
Configuration options are located in [application.properties](src/main/resources/application.properties) file.
