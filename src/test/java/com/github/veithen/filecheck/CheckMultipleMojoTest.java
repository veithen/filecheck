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

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

public class CheckMultipleMojoTest {
    @Rule public MojoRule rule = new MojoRule();

    @Test
    public void testOK() throws Exception {
        rule.lookupMojo("check-multiple", new File("src/test/files/pom-multiple.xml")).execute();
    }
}
