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
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.SelectorUtils;
import org.codehaus.plexus.util.StringUtils;

public abstract class AbstractCheckMojo extends AbstractMojo {
    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {
        for (IFileSet fileSet : getFileSets()) {
            File directory = fileSet.getDirectory();
            String[] expectedFiles = fileSet.getExpectedFiles();
            String[] allowedFiles = fileSet.getAllowedFiles();
            DirectoryScanner scanner = new DirectoryScanner();
            scanner.setBasedir(directory);
            scanner.scan();
            boolean[] matchedPatterns = new boolean[expectedFiles.length];
            List<String> unexpected = new ArrayList<String>();
            int expectedCount = 0;
            int allowedCount = 0;
            files:
            for (String file : scanner.getIncludedFiles()) {
                boolean hasMatch = false;
                for (int i = 0; i < expectedFiles.length; i++) {
                    if (SelectorUtils.matchPath(expectedFiles[i], file)) {
                        matchedPatterns[i] = true;
                        hasMatch = true;
                        // We can't exit the loop here because multiple patterns may be matched
                        // and we need to record all of these matches.
                    }
                }
                if (hasMatch) {
                    expectedCount++;
                    continue;
                }
                if (allowedFiles != null) {
                    for (String allowedFile : allowedFiles) {
                        if (SelectorUtils.matchPath(allowedFile, file)) {
                            allowedCount++;
                            continue files;
                        }
                    }
                }
                unexpected.add(file);
            }
            List<String> missing = new ArrayList<String>();
            for (int i = 0; i < expectedFiles.length; i++) {
                if (!matchedPatterns[i]) {
                    missing.add(expectedFiles[i]);
                }
            }
            getLog().info(
                            "Checked "
                                    + directory
                                    + ": "
                                    + expectedCount
                                    + " expected, "
                                    + allowedCount
                                    + " allowed, "
                                    + unexpected.size()
                                    + " unexpected, "
                                    + missing.size()
                                    + " missing");
            if (!missing.isEmpty()) {
                throw new MojoExecutionException(
                        "The following files are missing in "
                                + directory
                                + ": "
                                + StringUtils.join(missing.iterator(), ", "));
            }
            if (!unexpected.isEmpty()) {
                throw new MojoExecutionException(
                        "Unexpected files found in "
                                + directory
                                + ": "
                                + StringUtils.join(unexpected.iterator(), ", "));
            }
        }
    }

    protected abstract IFileSet[] getFileSets();
}
