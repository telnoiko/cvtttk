package com.crrvt.testtask.service;

import com.crrvt.testtask.dto.RequestFileDto;
import com.crrvt.testtask.util.CompressionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StreamingCompressionService implements CompressionService {

  public CompressionUtil compressor;

  @Override
  public void compress(final List<RequestFileDto> files, final OutputStream outputStream) throws IOException {
    log.info("Streaming files into response");
    compressor.compress(files, outputStream);
  }
}
