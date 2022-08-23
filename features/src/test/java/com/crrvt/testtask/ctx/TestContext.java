package com.crrvt.testtask.ctx;

import com.crrvt.testtask.client.CompressorClient;
import com.crrvt.testtask.util.ResourceUtil;

public record TestContext(
    ScenarioContext sctx,
    CompressorClient compressorClient,
    ResourceUtil resourceUtil
) {
}
