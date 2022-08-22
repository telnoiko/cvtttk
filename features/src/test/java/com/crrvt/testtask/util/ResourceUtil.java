package com.crrvt.testtask.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class ResourceUtil {

  private static final String RESOURCE_PATH = "build/resources/test/test-data/";
  private static final String EXTRACTED_PATH = RESOURCE_PATH + "extracted/";

  public File load(String path) {
    return new File(path);
  }

  public List<File> load(List<String> paths) {
    return paths.stream()
        .map(name -> RESOURCE_PATH + name)
        .map(this::load)
        .toList();
  }

  public Map<String, File> unzip(InputStream resourceInputStream) throws IOException {
    var extractedPath = Path.of(EXTRACTED_PATH);
    FileUtils.deleteDirectory(extractedPath.toFile());
    Files.createDirectory(extractedPath);

    try (ZipInputStream zipInputStream = new ZipInputStream(resourceInputStream)) {
      ZipEntry entry = zipInputStream.getNextEntry();
      while (entry != null) {
        log.info("Unzipping '{}'", entry.getName());
        Files.copy(zipInputStream, Path.of(extractedPath.toString(), entry.getName()), StandardCopyOption.REPLACE_EXISTING);
        entry = zipInputStream.getNextEntry();
      }
    }

    return this.listFiles(extractedPath);
  }

  private Map<String, File> listFiles(Path path) throws IOException {
    try (Stream<Path> paths = Files.walk(path)) {
      return paths
          .filter(Files::isRegularFile)
          .map(p -> Map.entry(p.toFile().getName(), p.toFile()))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
  }
}
