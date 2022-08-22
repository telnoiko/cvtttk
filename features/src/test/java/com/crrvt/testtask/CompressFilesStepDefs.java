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
        .header("Content-Disposition", StringContains.containsStringIgnoringCase(extension));
  }

  @And("^downloaded archive contains all files from request$")
  public void downloadedFileHasFilesInside() throws IOException {
    var filesToCompress = ctx.getSctx().getCompressRequest().getFiles();
    var responseInputStream = ctx.getSctx().getCompressResponse().getResponse().extract().body().asInputStream();
    var unzippedFiles = ctx.getResourceUtil().unzip(responseInputStream);

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
