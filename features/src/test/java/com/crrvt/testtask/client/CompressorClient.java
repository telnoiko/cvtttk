package com.crrvt.testtask.client;

import com.crrvt.testtask.model.CompressRequest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CompressorClient {

  public ValidatableResponse compress(CompressRequest compressRequest) {
    RequestSpecification request = given();
    request.contentType(compressRequest.getContentType());
    compressRequest.getFiles().forEach(file -> request.multiPart("fileName", file));
    return request.when().post("compress").then();
  }

  public ValidatableResponse health() {
    return given().get("/health").then();
  }

}
