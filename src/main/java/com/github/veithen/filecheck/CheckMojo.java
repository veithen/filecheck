package com.github.veithen.filecheck;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.SelectorUtils;
import org.codehaus.plexus.util.StringUtils;

@Mojo(name="check", defaultPhase=LifecyclePhase.GENERATE_SOURCES)
public class CheckMojo extends AbstractMojo {
    @Parameter(required=true)
    private File directory;
    
    @Parameter
    private String[] expectedFiles;
    
    @Parameter
    private String[] allowedFiles;

    public void execute() throws MojoExecutionException, MojoFailureException {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(directory);
        scanner.scan();
        boolean[] matchedPatterns = new boolean[expectedFiles.length];
        List<String> unexpected = new ArrayList<String>();
        files: for (String file : scanner.getIncludedFiles()) {
            boolean hasMatch = false;
            for (int i=0; i<expectedFiles.length; i++) {
                if (SelectorUtils.matchPath(expectedFiles[i], file)) {
                    matchedPatterns[i] = true;
                    hasMatch = true;
                    // We can't exit the loop here because multiple patterns may be matched
                    // and we need to record all of these matches.
                }
            }
            if (hasMatch) {
                continue;
            }
            if (allowedFiles != null) {
                for (String allowedFile : allowedFiles) {
                    if (SelectorUtils.matchPath(allowedFile, file)) {
                        continue files;
                    }
                }
            }
            unexpected.add(file);
        }
        List<String> missing = new ArrayList<String>();
        for (int i=0; i<expectedFiles.length; i++) {
            if (!matchedPatterns[i]) {
                missing.add(expectedFiles[i]);
            }
        }
        if (!missing.isEmpty()) {
            throw new MojoExecutionException("The following files are missing in " + directory + ": " + StringUtils.join(missing.iterator(), ", "));
        }
        if (!unexpected.isEmpty()) {
            throw new MojoExecutionException("Unexpected files found in " + directory + ": " + StringUtils.join(unexpected.iterator(), ", "));
        }
    }
}
