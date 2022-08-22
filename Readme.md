This is a test task to implement Archiver service which will accept
a list of files via `form-data` http request.  

[OpenApi Specification](openapi.yaml)

## Prerequisite 
- Java 17 
- Docker

## Build and Run
To run locally
```shell
make buildLocal
make runLocal
```

To run as a Docker container.  
After service has started up access [http://localhost:8080](http://localhost:8080) for testing.
```shell
make buildDocker
make runDocker
```

## Test
To run component tests (cucumber tests + local app in docker).  
Reports can be found in `features/reports/component-report.html`
```shell
make componentTest
```

