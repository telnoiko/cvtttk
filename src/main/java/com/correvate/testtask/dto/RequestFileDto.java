package com.correvate.testtask.dto;

import java.io.InputStream;

public record RequestFileDto(
    String fileName,
    Long size,
    InputStream inputStream
) {
}
