package com.crrvt.testtask.client;

import com.crrvt.testtask.model.CompressRequest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class CompressorClient {

  public CompressorClient() {
    String archiverUrl = System.getProperty("archiverUrl");
    Integer archiverPort = Integer.parseInt(System.getProperty("archiverPort"));
    log.info("Archiver base url '{}:{}'", archiverUrl, archiverPort);
    RestAssured.baseURI = archiverUrl;
    RestAssured.port = archiverPort;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  public ValidatableResponse compress(CompressRequest compressRequest) {
    RequestSpecification request = given();
    request.contentType(compressRequest.getContentType());
    compressRequest.getFiles().forEach(file -> request.multiPart("fileName", file));
    return request.when().post("/compress").then().log().headers();
  }

  public ValidatableResponse health() {
    return given().get("/health").then().log().all();
  }

}
