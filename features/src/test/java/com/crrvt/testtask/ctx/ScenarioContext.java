package com.crrvt.testtask.ctx;

import com.crrvt.testtask.model.CompressRequest;
import com.crrvt.testtask.model.CompressResponse;
import lombok.Value;

@Value
public class ScenarioContext {

  CompressRequest compressRequest;

  CompressResponse compressResponse;
}
