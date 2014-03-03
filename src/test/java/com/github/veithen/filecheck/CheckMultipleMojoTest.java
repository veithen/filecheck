package com.github.veithen.filecheck;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

public class CheckMultipleMojoTest {
    @Rule
    public MojoRule rule = new MojoRule();

    @Test
    public void testOK() throws Exception {
        rule.lookupMojo("check-multiple", new File("src/test/files/pom-multiple.xml")).execute();
    }
}
