package com.crrvt.testtask.ctx;

import com.crrvt.testtask.model.CompressRequest;
import com.crrvt.testtask.model.CompressResponse;

public record ScenarioContext(
    CompressRequest compressRequest,
    CompressResponse compressResponse
) {
}
