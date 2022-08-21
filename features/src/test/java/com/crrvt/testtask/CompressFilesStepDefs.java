package com.crrvt.testtask;

import com.crrvt.testtask.ctx.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.StringEndsWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@Slf4j
@AllArgsConstructor
public class CompressFilesStepDefs {

  private TestContext ctx;

  @Given("^files are:$")
  public void givenTheFollowingFiles(List<String> names) {
    var filesToCompress = ctx.getResourceUtil().load(names);
    ctx.getSctx().getCompressRequest().setFiles(filesToCompress);
  }

  @And("the Content-Type header is {string}")
  public void requestHeaderIsMultipartFormData(String contentType) {
    ctx.getSctx().getCompressRequest().setContentType(contentType);
  }

  @When("^I request to compress the files$")
  public void iRequestToUploadTheFiles() {
    var compressRequest = ctx.getSctx().getCompressRequest();
    var response = ctx.getCompressorClient().compress(compressRequest);
    ctx.getSctx().getCompressResponse().setResponse(response);
  }

  @Then("the response status is {int}")
  public void theResponseIs(int code) {
    ctx.getSctx().getCompressResponse().getResponse().statusCode(code);
  }

  @And("the extension of downloaded file is {string}")
  public void theExtensionOfDownloadedFileIsZip(String extension) {
    ctx.getSctx().getCompressResponse().getResponse()
        .header("Content-Disposition", StringEndsWith.endsWith(extension));
  }

  @And("^downloaded archive contains all files from request$")
  public void downloadedFileHasFilesInside() throws IOException {
    var filesToCompress = ctx.getSctx().getCompressRequest().getFiles();
    var responseInputStream = ctx.getSctx().getCompressResponse().getResponse().extract().body().asInputStream();
    var unzippedFiles = ctx.getResourceUtil().unzip(responseInputStream);

    assertThat(filesToCompress, containsInAnyOrder(unzippedFiles));
  }

}
