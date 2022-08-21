package com.crrvt.testtask.client;

import com.crrvt.testtask.model.CompressRequest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.MultiPartConfig.multiPartConfig;

public class CompressorClient {

  public CompressorClient() {
    given().config(config()
        .multiPartConfig(multiPartConfig().defaultControlName("fileNames")
    ));
  }

  public ValidatableResponse compress(CompressRequest compressRequest) {
    RequestSpecification request = given();
    request.contentType(compressRequest.getContentType());
    compressRequest.getFiles().forEach(request::multiPart);
    return request.when().post("compress").then();
  }

  public ValidatableResponse health() {
    return given().get("/health").then();
  }

}
