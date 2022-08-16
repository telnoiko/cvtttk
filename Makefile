.PHONY: build

build:
	./gradlew clean bootJar
	docker build --build-arg JAR_FILE=build/libs/\*.jar -t archiver .

run:
	docker run -p 80:8080 archiver
