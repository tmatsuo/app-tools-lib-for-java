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

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Resolve paths with CloudSdk and Python defaults.
 */
public enum PathResolver {

  INSTANCE;

  /**
   * Attempts to find the path to Google Cloud SDK.
   *
   * @return Path to Google Cloud SDK or null
   */
  public Path getCloudSdkPath() {
    List<String> possiblePaths = new ArrayList<>();

    // search system environment PATH
    getLocationsFromPath(possiblePaths);

    // try environment variable GOOGLE_CLOUD_SDK_HOME
    possiblePaths.add(System.getenv("GOOGLE_CLOUD_SDK_HOME"));

    // search program files
    if (System.getProperty("os.name").contains("Windows")) {
      possiblePaths.add(getProgramFilesLocation());
    } else {
      // home dir
      possiblePaths.add(System.getProperty("user.home") + "/google-cloud-sdk");
      // try devshell VM
      possiblePaths.add("/google/google-cloud-sdk");
      // try bitnami Jenkins VM:
      possiblePaths.add("/usr/local/share/google/google-cloud-sdk");
    }

    return searchPaths(possiblePaths);
  }

  private void getLocationsFromPath(List<String> possiblePaths) {
    String pathEnv = System.getenv("PATH");
    if (pathEnv != null) {
      for (String path : pathEnv.split(File.pathSeparator)) {
        // strip out trailing path separator
        if (path.endsWith(File.separator)) {
          path = path.substring(0, path.length() - 1);
        }
        if (path.endsWith("google-cloud-sdk" + File.separator + "bin")) {
          possiblePaths.add(path.substring(0, path.length() - 4));
        }
      }
    }
  }

  private String getProgramFilesLocation() {
    String programFiles = System.getenv("ProgramFiles");
    if (programFiles == null) {
      programFiles = System.getenv("ProgramFiles(x86)");
    }
    if (programFiles != null) {
      return programFiles + "\\Google\\Cloud SDK\\google-cloud-sdk";
    } else {
      return null;
    }
  }

  private Path searchPaths(List<String> possiblePaths) {
    for (String pathString : possiblePaths) {
      if (pathString != null) {
        File file = new File(pathString);
        if (file.exists()) {
          return file.toPath();
        }
      }
    }
    return null;
  }
}
