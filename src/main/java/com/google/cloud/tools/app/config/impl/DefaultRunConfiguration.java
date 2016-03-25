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
package com.google.cloud.tools.app.config.impl;

import com.google.appengine.repackaged.com.google.api.client.util.Preconditions;
import com.google.cloud.tools.app.config.RunConfiguration;
import com.google.common.collect.ImmutableList;

import java.nio.file.Path;
import java.util.Collection;

import javax.annotation.Nullable;

/**
 * Default implementation of {@link RunConfiguration}.
 */
public class DefaultRunConfiguration implements RunConfiguration {

  private final Collection<Path> appYamls;
  private final Boolean synchronous;
  private final String host;
  private final Integer port;
  private final String adminHost;
  private final Integer adminPort;
  private final String authDomain;
  private final String storagePath;
  private final String logLevel;
  private final Integer maxModuleInstances;
  private final Boolean useMtimeFileWatcher;
  private final Boolean threadsafeOverride;
  private final String pythonStartupScript;
  private final String pythonStartupArgs;
  private final Collection<String> jvmFlags;
  private final String customEntrypoint;
  private final String runtime;
  private final Boolean allowSkippedFiles;
  private final Integer apiPort;
  private final Boolean automaticRestart;
  private final String devAppserverLogLevel;
  private final Boolean skipSdkUpdateCheck;
  private final String defaultGcsBucketName;

  private DefaultRunConfiguration(Collection<Path> appYamls, @Nullable Boolean synchronous,
      @Nullable String host, @Nullable Integer port, @Nullable String adminHost,
      @Nullable Integer adminPort, @Nullable String authDomain, @Nullable String storagePath,
      @Nullable String logLevel, @Nullable Integer maxModuleInstances,
      @Nullable Boolean useMtimeFileWatcher, @Nullable Boolean threadsafeOverride,
      @Nullable String pythonStartupScript, @Nullable String pythonStartupArgs,
      @Nullable Collection<String> jvmFlags, @Nullable String customEntrypoint,
      @Nullable String runtime, @Nullable Boolean allowSkippedFiles, @Nullable Integer apiPort,
      @Nullable Boolean automaticRestart, @Nullable String devAppserverLogLevel,
      @Nullable Boolean skipSdkUpdateCheck, @Nullable String defaultGcsBucketName) {
    this.appYamls = appYamls;
    this.synchronous = synchronous;
    this.host = host;
    this.port = port;
    this.adminHost = adminHost;
    this.adminPort = adminPort;
    this.authDomain = authDomain;
    this.storagePath = storagePath;
    this.logLevel = logLevel;
    this.maxModuleInstances = maxModuleInstances;
    this.useMtimeFileWatcher = useMtimeFileWatcher;
    this.threadsafeOverride = threadsafeOverride;
    this.pythonStartupScript = pythonStartupScript;
    this.pythonStartupArgs = pythonStartupArgs;
    this.jvmFlags = jvmFlags;
    this.customEntrypoint = customEntrypoint;
    this.runtime = runtime;
    this.allowSkippedFiles = allowSkippedFiles;
    this.apiPort = apiPort;
    this.automaticRestart = automaticRestart;
    this.devAppserverLogLevel = devAppserverLogLevel;
    this.skipSdkUpdateCheck = skipSdkUpdateCheck;
    this.defaultGcsBucketName = defaultGcsBucketName;
  }

  public Collection<Path> getAppYamls() {
    return appYamls;
  }

  public Boolean isSynchronous() {
    return synchronous;
  }

  public String getHost() {
    return host;
  }

  public Integer getPort() {
    return port;
  }

  public String getAdminHost() {
    return adminHost;
  }

  public Integer getAdminPort() {
    return adminPort;
  }

  public String getAuthDomain() {
    return authDomain;
  }

  public String getStoragePath() {
    return storagePath;
  }

  public String getLogLevel() {
    return logLevel;
  }

  public Integer getMaxModuleInstances() {
    return maxModuleInstances;
  }

  public Boolean isUseMtimeFileWatcher() {
    return useMtimeFileWatcher;
  }

  public Boolean isThreadsafeOverride() {
    return threadsafeOverride;
  }

  public String getPythonStartupScript() {
    return pythonStartupScript;
  }

  public String getPythonStartupArgs() {
    return pythonStartupArgs;
  }

  public Collection<String> getJvmFlags() {
    return jvmFlags;
  }

  public String getCustomEntrypoint() {
    return customEntrypoint;
  }

  public String getRuntime() {
    return runtime;
  }

  public Boolean isAllowSkippedFiles() {
    return allowSkippedFiles;
  }

  public Integer getApiPort() {
    return apiPort;
  }

  public Boolean isAutomaticRestart() {
    return automaticRestart;
  }

  public String getDevAppserverLogLevel() {
    return devAppserverLogLevel;
  }

  public Boolean isSkipSdkUpdateCheck() {
    return skipSdkUpdateCheck;
  }

  public String getDefaultGcsBucketName() {
    return defaultGcsBucketName;
  }

  public static Builder newBuilder(Path... appYamls) {
    Preconditions.checkArgument(appYamls.length > 0);

    return new Builder(ImmutableList.copyOf(appYamls));
  }

  private static class Builder {
    private Collection<Path> appYamls;
    private Boolean synchronous;
    private String host;
    private Integer port;
    private String adminHost;
    private Integer adminPort;
    private String authDomain;
    private String storagePath;
    private String logLevel;
    private Integer maxModuleInstances;
    private Boolean useMtimeFileWatcher;
    private Boolean threadsafeOverride;
    private String pythonStartupScript;
    private String pythonStartupArgs;
    private Collection<String> jvmFlags;
    private String customEntrypoint;
    private String runtime;
    private Boolean allowSkippedFiles;
    private Integer apiPort;
    private Boolean automaticRestart;
    private String devAppserverLogLevel;
    private Boolean skipSdkUpdateCheck;
    private String defaultGcsBucketName;

    private Builder(Collection<Path> appYamls) {
      this.appYamls = appYamls;
    }

    public Builder synchronous(Boolean synchronous) {
      this.synchronous = synchronous;
      return this;
    }

    public Builder host(String host) {
      this.host = host;
      return this;
    }

    public Builder port(Integer port) {
      this.port = port;
      return this;
    }

    public Builder adminHost(String adminHost) {
      this.adminHost = adminHost;
      return this;
    }

    public Builder adminPort(Integer adminPort) {
      this.adminPort = adminPort;
      return this;
    }

    public Builder authDomain(String authDomain) {
      this.authDomain = authDomain;
      return this;
    }

    public Builder storagePath(String storagePath) {
      this.storagePath = storagePath;
      return this;
    }

    public Builder logLevel(String logLevel) {
      this.logLevel = logLevel;
      return this;
    }

    public Builder maxModuleInstances(Integer maxModuleInstances) {
      this.maxModuleInstances = maxModuleInstances;
      return this;
    }

    public Builder useMtimeFileWatcher(Boolean useMtimeFileWatcher) {
      this.useMtimeFileWatcher = useMtimeFileWatcher;
      return this;
    }

    public Builder threadsafeOverride(Boolean threadsafeOverride) {
      this.threadsafeOverride = threadsafeOverride;
      return this;
    }

    public Builder pythonStartupScript(String pythonStartupScript) {
      this.pythonStartupScript = pythonStartupScript;
      return this;
    }

    public Builder pythonStartupArgs(String pythonStartupArgs) {
      this.pythonStartupArgs = pythonStartupArgs;
      return this;
    }

    public Builder jvmFlags(Collection<String> jvmFlags) {
      this.jvmFlags = jvmFlags;
      return this;
    }

    public Builder customEntrypoint(String customEntrypoint) {
      this.customEntrypoint = customEntrypoint;
      return this;
    }

    public Builder runtime(String runtime) {
      this.runtime = runtime;
      return this;
    }

    public Builder allowSkippedFiles(Boolean allowSkippedFiles) {
      this.allowSkippedFiles = allowSkippedFiles;
      return this;
    }

    public Builder apiPort(Integer apiPort) {
      this.apiPort = apiPort;
      return this;
    }

    public Builder automaticRestart(Boolean automaticRestart) {
      this.automaticRestart = automaticRestart;
      return this;
    }

    public Builder devAppserverLogLevel(String devAppserverLogLevel) {
      this.devAppserverLogLevel = devAppserverLogLevel;
      return this;
    }

    public Builder skipSdkUpdateCheck(Boolean skipSdkUpdateCheck) {
      this.skipSdkUpdateCheck = skipSdkUpdateCheck;
      return this;
    }

    public Builder defaultGcsBucketName(String defaultGcsBucketName) {
      this.defaultGcsBucketName = defaultGcsBucketName;
      return this;
    }

    public RunConfiguration build() {
      return new DefaultRunConfiguration(appYamls, synchronous, host, port, adminHost, adminPort,
          authDomain, storagePath, logLevel, maxModuleInstances, useMtimeFileWatcher,
          threadsafeOverride, pythonStartupScript, pythonStartupArgs, jvmFlags, customEntrypoint,
          runtime, allowSkippedFiles, apiPort, automaticRestart, devAppserverLogLevel,
          skipSdkUpdateCheck, defaultGcsBucketName);
    }
  }
}
