package com.crrvt.testtask;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class CompressFilesStepDefs {

  @Given("^the following files:$")
  public void givenTheFollowingFiles(List<String> path) {
    System.out.println("givenTheFollowingFiles");
  }

  @When("^I request to upload the files$")
  public void iRequestToUploadTheFiles() {
    System.out.println("iRequestToUploadTheFiles");
//    throw new RuntimeException();
  }

  @Then("the response is {int}")
  public void theResponseIs(int arg0) {
    System.out.println("theResponseIs");
  }

  @And("^the extension of downloaded file is .zip$")
  public void theExtensionOfDownloadedFileIsZip() {
    System.out.println("theExtensionOfDownloadedFileIsZip");
  }

  @And("^downloaded file has following files:$")
  public void downloadedFileHasFilesInside(List<String> path) {
    System.out.println("downloadedFileHasFilesInside");
  }

}
