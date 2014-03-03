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
