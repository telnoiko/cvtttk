package com.crrvt.testtask.service;

import com.crrvt.testtask.dto.RequestFileDto;
import com.crrvt.testtask.repository.ArchiveRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@ConditionalOnProperty(name="archiver.persist", havingValue = "true")
public class PersistingCompressionService implements CompressionService {

  private ArchiveRepository repository;

  @Override
  public void compress(final List<RequestFileDto> files, final OutputStream outputStream) throws IOException {
    final var archivedFile = repository.save(files);
    sendToOutputStream(archivedFile, outputStream);
  }

  private void sendToOutputStream(File archivedFile, OutputStream outputStream) throws IOException {
    final var fis = new FileInputStream(archivedFile);
    StreamUtils.copy(fis, outputStream);
  }
}
