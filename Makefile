.PHONY: build

buildLocal:
	./gradlew clean build

buildDocker:
	./gradlew clean bootJar
	docker build --build-arg JAR_FILE=build/libs/\*.jar -t archiver .

componentTest:
	./gradlew componentTest

runLocal:
	./gradlew bootRun

runDocker:
	docker run -p 8080:8080 archiver
