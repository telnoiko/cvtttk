package com.correvate.testtask.mapper;

import com.correvate.testtask.dto.RequestFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
public class MultipartFileMapper {

  public RequestFileDto mapToFileDto(MultipartFile file) {
    try {
      return new RequestFileDto(file.getOriginalFilename(), file.getSize(), file.getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
