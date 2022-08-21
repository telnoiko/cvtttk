package com.crrvt.testtask.ctx;

import com.crrvt.testtask.client.CompressorClient;
import com.crrvt.testtask.util.ResourceUtil;
import lombok.Value;

@Value
public class TestContext {

  ScenarioContext sctx;

  CompressorClient compressorClient;

  ResourceUtil resourceUtil;

}
