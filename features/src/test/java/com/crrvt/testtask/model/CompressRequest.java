package com.crrvt.testtask.model;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class CompressRequest {

  private List<File> files;

  private String contentType;
}
