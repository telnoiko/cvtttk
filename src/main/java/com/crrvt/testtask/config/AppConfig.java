package com.crrvt.testtask.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class AppConfig {

  @Bean
  MultipartConfigElement multipartConfigElement(@Value("${archiver.maxReqSize}") Long maxReqSize) {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize(DataSize.ofBytes(maxReqSize));
    factory.setMaxRequestSize(DataSize.ofBytes(maxReqSize));
    return factory.createMultipartConfig();
  }
}
