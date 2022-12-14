package com.crrvt.testtask;

import com.crrvt.testtask.ctx.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.hamcrest.core.StringContains;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@AllArgsConstructor
public class CompressFilesStepDefs {

  private TestContext ctx;

  @Given("^files are:$")
  public void givenTheFollowingFiles(List<String> names) {
    var filesToCompress = ctx.resourceUtil().load(names);
    ctx.sctx().compressRequest().setFiles(filesToCompress);
  }

  @And("the Content-Type header is {string}")
  public void requestHeaderIsMultipartFormData(String contentType) {
    ctx.sctx().compressRequest().setContentType(contentType);
  }

  @When("^I request to compress the files$")
  public void iRequestToUploadTheFiles() {
    var compressRequest = ctx.sctx().compressRequest();
    var response = ctx.compressorClient().compress(compressRequest);
    ctx.sctx().compressResponse().setResponse(response);
  }

  @Then("the response status is {int}")
  public void theResponseIs(int code) {
    ctx.sctx().compressResponse().getResponse().statusCode(code);
  }

  @And("the extension of downloaded file is {string}")
  public void theExtensionOfDownloadedFileIsZip(String extension) {
    ctx.sctx().compressResponse().getResponse()
        .header("Content-Disposition", StringContains.containsStringIgnoringCase(extension));
  }

  @And("^downloaded archive contains all files from request$")
  public void downloadedFileHasFilesInside() throws IOException {
    var filesToCompress = ctx.sctx().compressRequest().getFiles();
    var responseInputStream = ctx.sctx().compressResponse().getResponse().extract().body().asInputStream();
    var unzippedFiles = ctx.resourceUtil().unzip(responseInputStream);

    assertThat(filesToCompress.size(), is(equalTo(unzippedFiles.size())));
    assertFilesEqual(filesToCompress, unzippedFiles);
  }

  private static void assertFilesEqual(List<File> filesToCompress, Map<String, File> unzippedFiles) {
    filesToCompress.forEach(requestedFile -> {
      File unzippedFile = unzippedFiles.get(requestedFile.getName());
      try {
        assertTrue(FileUtils.contentEquals(requestedFile, unzippedFile));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
