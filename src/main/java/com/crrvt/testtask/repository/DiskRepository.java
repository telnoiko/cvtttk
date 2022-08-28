package com.crrvt.testtask.repository;

import com.crrvt.testtask.dto.RequestFileDto;
import com.crrvt.testtask.util.CompressionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class DiskRepository implements ArchiveRepository {

  private CompressionUtil compressor;

  @Override
  public File save(final List<RequestFileDto> files) throws IOException {
    final var archiveFile = new File(System.currentTimeMillis() + ".zip");
    log.info("Writing into {}", archiveFile.getAbsolutePath());
    final var archiveStream = new FileOutputStream(archiveFile);
    compressor.compress(files, archiveStream);
    return archiveFile;
  }
}
