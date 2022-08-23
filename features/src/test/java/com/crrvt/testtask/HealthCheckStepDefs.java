package com.crrvt.testtask;

import com.crrvt.testtask.ctx.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;

@AllArgsConstructor
public class HealthCheckStepDefs {

  private TestContext ctx;

  @When("I check health status")
  public void iRequestToHealthEndpoint() {
    var response = ctx.compressorClient().health();
    ctx.sctx().compressResponse().setResponse(response);
  }

  @And("the response value at path {string} equals {string}")
  public void theResponseBodyStatusIsUP(String path, String value) {
    ValidatableResponse response = ctx.sctx().compressResponse().getResponse();
    response.body(path, Matchers.equalToIgnoringCase(value));
  }
}
