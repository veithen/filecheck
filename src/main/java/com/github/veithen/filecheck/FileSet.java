/*-
 * #%L
 * Filecheck Maven Plugin
 * %%
 * Copyright (C) 2014 - 2018 Andreas Veithen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.veithen.filecheck;

import java.io.File;

public class FileSet implements IFileSet {
    private File directory;
    private String[] expectedFiles;
    private String[] allowedFiles;

    @Override
    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    @Override
    public String[] getExpectedFiles() {
        return expectedFiles;
    }

    public void setExpectedFiles(String[] expectedFiles) {
        this.expectedFiles = expectedFiles;
    }

    @Override
    public String[] getAllowedFiles() {
        return allowedFiles;
    }

    public void setAllowedFiles(String[] allowedFiles) {
        this.allowedFiles = allowedFiles;
    }
}
