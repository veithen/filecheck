package com.github.veithen.filecheck;

import java.io.File;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="check")
public class CheckMojo extends AbstractCheckMojo implements IFileSet {
    @Parameter(required=true)
    private File directory;
    
    @Parameter
    private String[] expectedFiles;
    
    @Parameter
    private String[] allowedFiles;

    public File getDirectory() {
        return directory;
    }

    public String[] getExpectedFiles() {
        return expectedFiles;
    }

    public String[] getAllowedFiles() {
        return allowedFiles;
    }

    @Override
    protected IFileSet[] getFileSets() {
        return new IFileSet[] { this };
    }
}
