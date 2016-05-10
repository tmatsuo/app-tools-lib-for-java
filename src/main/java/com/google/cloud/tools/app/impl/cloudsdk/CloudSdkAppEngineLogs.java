/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.tools.app.impl.cloudsdk;

import com.google.cloud.tools.app.api.AppEngineException;
import com.google.cloud.tools.app.api.logs.AppEngineLogs;
import com.google.cloud.tools.app.api.logs.LogsConfiguration;
import com.google.cloud.tools.app.impl.cloudsdk.internal.process.ProcessRunnerException;
import com.google.cloud.tools.app.impl.cloudsdk.internal.sdk.CloudSdk;
import com.google.cloud.tools.app.impl.cloudsdk.util.Args;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Cloud SDK based implementation of {@link AppEngineLogs}.
 */
public class CloudSdkAppEngineLogs implements AppEngineLogs {

  private CloudSdk sdk;

  public CloudSdkAppEngineLogs(
      CloudSdk sdk) {
    this.sdk = sdk;
  }

  private void execute(List<String> arguments) throws AppEngineException {
    try {
      sdk.runAppCommand(arguments);
    } catch (ProcessRunnerException e) {
      throw new AppEngineException(e);
    }
  }

  /**
   * Read log entries.
   */
  @Override
  public void read(LogsConfiguration configuration) {
    Preconditions.checkNotNull(configuration);
    Preconditions.checkNotNull(sdk);

    List<String> arguments = new ArrayList<>();
    arguments.add("logs");
    arguments.add("read");
    arguments.addAll(Args.string("level", configuration.getLevel()));
    arguments.addAll(Args.string("version", configuration.getVersion()));
    arguments.addAll(Args.string("service", configuration.getService()));
    arguments.addAll(Args.integer("limit", configuration.getLimit()));

    execute(arguments);
  }
}