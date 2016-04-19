/**
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
package com.google.cloud.tools.app.module;

import com.google.cloud.tools.app.AppAction;
import com.google.cloud.tools.app.GCloudExecutionException;
import com.google.cloud.tools.app.ProcessCaller.Tool;
import com.google.cloud.tools.app.config.module.GetLogsConfiguration;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gets the logs for a version of a module.
 */
public class GetLogsAction extends AppAction {

  private GetLogsConfiguration configuration;

  public GetLogsAction(GetLogsConfiguration configuration) {
    Preconditions.checkNotNull(configuration);
    Preconditions.checkNotNull(configuration.getModules());
    Preconditions.checkArgument(configuration.getModules().size() > 0);
    Preconditions.checkArgument(!Strings.isNullOrEmpty(configuration.getVersion()));

    this.configuration = configuration;
  }

  public boolean execute() throws GCloudExecutionException, IOException {
    List<String> arguments = new ArrayList<>();
    arguments.add("modules");
    arguments.add("get-logs");
    arguments.addAll(configuration.getModules());
    // TODO(joaomartins): Check if file is valid.
    // TODO(joaomartins): Should we disallow empty files? Printing to stdout will be cluttered
    // by Maven's artifacts, for example.
    if (!Strings.isNullOrEmpty(configuration.getLogFileLocation())) {
      arguments.add(configuration.getLogFileLocation());
    }
    arguments.add("--version");
    arguments.add(configuration.getVersion());
    if (configuration.isAppend()) {
      arguments.add("--append");
    }
    if (configuration.getDays() != null) {
      arguments.add("--days");
      arguments.add(String.valueOf(configuration.getDays()));
    }
    if (configuration.isDetails()) {
      arguments.add("--details");
    }
    if (!Strings.isNullOrEmpty(configuration.getEndDate())) {
      arguments.add("--end-date");
      arguments.add(configuration.getEndDate());
    }
    if (!Strings.isNullOrEmpty(configuration.getServer())) {
      arguments.add("--server");
      arguments.add(configuration.getServer());
    }
    if (!Strings.isNullOrEmpty(configuration.getSeverity())) {
      arguments.add("--severity");
      arguments.add(configuration.getSeverity());
    }
    if (!Strings.isNullOrEmpty(configuration.getVhost())) {
      arguments.add("--vhost");
      arguments.add(configuration.getVhost());
    }

    return processCallerFactory.newProcessCaller(Tool.GCLOUD, arguments).call();
  }
}