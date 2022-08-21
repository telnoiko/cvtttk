package com.crrvt.testtask.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

public class ResourceUtil {

  private static final String RESOURCE_PATH = "features/test/resources/test-data";
  private static final String EXTRACTED_PATH = RESOURCE_PATH + "/extracted";

  public File load(String path) {
    return new File(path);
  }

  public List<File> load(List<String> paths) {
    return paths.stream()
        .map(name -> RESOURCE_PATH + name)
        .map(this::load)
        .toList();
  }

  public List<File> unzip(InputStream resourceInputStream) throws IOException {
    var extractedPath = Path.of(EXTRACTED_PATH);
    if (Files.notExists(extractedPath)) {
      Files.createDirectories(extractedPath);
    }
    try (ZipInputStream zipInputStream = new ZipInputStream(resourceInputStream)) {
      while (zipInputStream.available() != 0) {
        zipInputStream.getNextEntry();
        Files.copy(zipInputStream, extractedPath, StandardCopyOption.REPLACE_EXISTING);
      }
    }
    return this.listFiles(extractedPath);
  }

  private List<File> listFiles(Path path) throws IOException {
    try (Stream<Path> paths = Files.walk(path)) {
      return paths
          .filter(Files::isRegularFile)
          .map(Path::toFile)
          .toList();
    }
  }
}
