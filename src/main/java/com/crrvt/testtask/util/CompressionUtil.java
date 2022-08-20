package com.crrvt.testtask.util;

import com.crrvt.testtask.dto.RequestFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Component
public class CompressionUtil {

  public void compress(List<RequestFileDto> files, OutputStream outputStream) throws IOException {
    try (ZipOutputStream zipStream = new ZipOutputStream(outputStream)) {
      for (RequestFileDto requestFile : files) {
        log.info("Adding file '{}' of size {} bytes into archive", requestFile.fileName(), requestFile.size());
        var entry = new ZipEntry(requestFile.fileName());
        entry.setSize(requestFile.size());
        entry.setTime(System.currentTimeMillis());
        zipStream.putNextEntry(entry);
        StreamUtils.copy(requestFile.inputStream(), zipStream);
        zipStream.closeEntry();
      }
      log.info("Compressed successfully");
      zipStream.flush();
    }
  }
}
