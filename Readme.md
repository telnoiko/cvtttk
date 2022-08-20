This is a test task to implement Archiver service which will accept
a list of files via `form-data` http request.  

[OpenApi Specification](openapi.yaml)

## Build and Run
Prerequisite is having Java 17 and Docker installed

To run locally
```shell
make buildLocal
make runLocal
```

To run as a Docker container
```shell
make buildDocker
make runDocker
```

After service has started up access [http://localhost:8080](http://localhost:8080) for testing.

## Test

To run component tests (cucumber tests + local app in docker)
```shell
make componentTest
```