package com.correvate.testtask.service;

import com.correvate.testtask.dto.RequestFileDto;
import com.correvate.testtask.util.CompressionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Slf4j
//@Service
@AllArgsConstructor
public class MemoryCompressionService implements CompressionService {

  public CompressionUtil compressor;

  @Override
  public void compress(final List<RequestFileDto> files, final OutputStream outputStream) throws IOException {
    log.info("Writing files into disk");
    final var archiveFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".zip");
    final var archiveStream = new FileOutputStream(archiveFile);
    compressor.compress(files, archiveStream);

    final var archiveResource = new FileSystemResource(archiveFile);
    StreamUtils.copy(archiveResource.getInputStream(), outputStream);
  }
}
