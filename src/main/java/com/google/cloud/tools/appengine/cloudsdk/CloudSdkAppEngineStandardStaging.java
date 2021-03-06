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

package com.google.cloud.tools.appengine.cloudsdk;

import com.google.cloud.tools.appengine.api.AppEngineException;
import com.google.cloud.tools.appengine.api.deploy.AppEngineStandardStaging;
import com.google.cloud.tools.appengine.api.deploy.StageStandardConfiguration;
import com.google.cloud.tools.appengine.cloudsdk.internal.args.AppCfgArgs;
import com.google.cloud.tools.appengine.cloudsdk.internal.process.ProcessRunnerException;
import com.google.common.base.Preconditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of {@link AppEngineStandardStaging} that uses App Engine SDK bundled with the
 * Cloud SDK.
 */
public class CloudSdkAppEngineStandardStaging implements AppEngineStandardStaging {

  private CloudSdk cloudSdk;

  public CloudSdkAppEngineStandardStaging(
      CloudSdk cloudSdk) {
    this.cloudSdk = cloudSdk;
  }

  @Override
  public void stageStandard(StageStandardConfiguration config) throws AppEngineException {
    Preconditions.checkNotNull(config);
    Preconditions.checkNotNull(config.getSourceDirectory());
    Preconditions.checkNotNull(config.getStagingDirectory());
    Preconditions.checkNotNull(cloudSdk);

    List<String> arguments = new ArrayList<>();

    arguments.addAll(AppCfgArgs.get("enable_quickstart", config.getEnableQuickstart()));
    arguments.addAll(AppCfgArgs.get("disable_update_check", config.getDisableUpdateCheck()));
    arguments.addAll(AppCfgArgs.get("enable_jar_splitting", config.getEnableJarSplitting()));
    arguments.addAll(AppCfgArgs.get("jar_splitting_excludes", config.getJarSplittingExcludes()));
    arguments.addAll(AppCfgArgs.get("compile_encoding", config.getCompileEncoding()));
    arguments.addAll(AppCfgArgs.get("delete_jsps", config.getDeleteJsps()));
    arguments.addAll(AppCfgArgs.get("enable_jar_classes", config.getEnableJarClasses()));
    arguments.addAll(AppCfgArgs.get("disable_jar_jsps", config.getDisableJarJsps()));
    if (config.getRuntime() != null) {
      // currently only java7 is allowed without --allow_any_runtime
      arguments.addAll(AppCfgArgs.get("allow_any_runtime", true));
      arguments.addAll(AppCfgArgs.get("runtime", config.getRuntime()));
    }
    arguments.add("stage");
    arguments.add(config.getSourceDirectory().toPath().toString());
    arguments.add(config.getStagingDirectory().toPath().toString());

    Path dockerfile = config.getDockerfile() == null ? null : config.getDockerfile().toPath();

    try {

      if (dockerfile != null && dockerfile.toFile().exists()) {
        Files.copy(dockerfile, config.getSourceDirectory().toPath()
            .resolve(dockerfile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
      }

      cloudSdk.runAppCfgCommand(arguments);

    } catch (IOException | ProcessRunnerException e) {
      throw new AppEngineException(e);
    }

  }

}
