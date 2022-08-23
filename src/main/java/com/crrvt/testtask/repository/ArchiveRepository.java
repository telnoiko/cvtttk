package com.crrvt.testtask.repository;

import com.crrvt.testtask.dto.RequestFileDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ArchiveRepository {
  File save(List<RequestFileDto> files) throws IOException;
}
