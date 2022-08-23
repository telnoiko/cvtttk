package com.crrvt.testtask.controller;

import com.crrvt.testtask.mapper.MultipartFileMapper;
import com.crrvt.testtask.service.CompressionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class UploadController {

  private static final HttpHeaders UPLOAD_HEADERS = new HttpHeaders();

  static {
    UPLOAD_HEADERS.setContentType(MediaType.valueOf("application/zip"));
    UPLOAD_HEADERS.setContentDisposition(ContentDisposition.attachment().filename("download.zip").build());
  }

  private final MultipartFileMapper mapper;
  private final CompressionService compressionService;

  @PostMapping("/compress")
  public ResponseEntity<StreamingResponseBody> compressFiles(
      @RequestPart(name = "fileName") List<MultipartFile> multipartFiles) {
    log.info("Received {} files to compress", multipartFiles.size());
    final var files = multipartFiles.stream().map(mapper::mapToFileDto).toList();
    return ResponseEntity.ok()
        .headers(UPLOAD_HEADERS)
        .body(outputStream -> compressionService.compress(files, outputStream));
  }
}
