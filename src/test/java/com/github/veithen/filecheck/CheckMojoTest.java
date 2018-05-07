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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

public class CheckMojoTest {
    @Rule
    public MojoRule rule = new MojoRule();
    
    @Test(expected=MojoExecutionException.class)
    public void testMissingFile() throws Exception {
        rule.lookupMojo("check", new File("src/test/files/pom-missing-file.xml")).execute();
    }
    
    @Test(expected=MojoExecutionException.class)
    public void testUnexpectedFile() throws Exception {
        rule.lookupMojo("check", new File("src/test/files/pom-unexpected-file.xml")).execute();
    }

    @Test
    public void testAllowed() throws Exception {
        rule.lookupMojo("check", new File("src/test/files/pom-allowed.xml")).execute();
    }
}
