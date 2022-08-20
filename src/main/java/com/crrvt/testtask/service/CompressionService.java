package com.crrvt.testtask.service;

import com.crrvt.testtask.dto.RequestFileDto;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Compresses and writes files into provided {@linkplain java.io.OutputStream}.
 */
public interface CompressionService {

  void compress(final List<RequestFileDto> files, final OutputStream outputStream) throws IOException;
}
