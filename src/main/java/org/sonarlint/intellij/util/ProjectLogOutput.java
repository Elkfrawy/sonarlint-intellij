/**
 * SonarLint for IntelliJ IDEA
 * Copyright (C) 2015 SonarSource
 * sonarlint@sonarsource.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonarlint.intellij.util;

import org.sonarlint.intellij.config.project.SonarLintProjectSettings;
import org.sonarlint.intellij.ui.SonarLintConsole;
import org.sonarsource.sonarlint.core.client.api.common.LogOutput;

public class ProjectLogOutput implements LogOutput {
  private SonarLintConsole console;
  private SonarLintProjectSettings settings;

  public ProjectLogOutput(SonarLintConsole console, SonarLintProjectSettings settings) {
    this.console = console;
    this.settings = settings;
  }

  @Override
  public void log(String msg, Level level) {
    if(!settings.isAnalysisLogsEnabled()) {
      return;
    }
    switch (level) {
      case TRACE:
      case DEBUG:
        console.debug(msg);
        break;
      case ERROR:
        console.error(msg);
        break;
      case INFO:
      case WARN:
      default:
        console.info(msg);
    }
  }
}
