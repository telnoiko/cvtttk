.PHONY: build

build:
	./gradlew clean bootJar
	docker build --build-arg JAR_FILE=build/libs/\*.jar -t archiver .

run:
	docker run -p 8080:8080 archiver
