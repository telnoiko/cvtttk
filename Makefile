.PHONY: build

build:
	./gradlew clean build

run:
	./gradlew bootRun

buildDocker:
	./gradlew clean bootJar
	docker build -f docker/app.Dockerfile --build-arg JAR_FILE=build/libs/\*.jar -t archiver .

runDocker:
	docker run -p 8080:8080 archiver

alpha:
	./gradlew alphaTest

alphaDocker:
	docker-compose -f docker/docker-compose.yaml --verbose up --force-recreate --abort-on-container-exit testrunner
