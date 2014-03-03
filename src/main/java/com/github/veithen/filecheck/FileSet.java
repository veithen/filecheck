package com.github.veithen.filecheck;

import java.io.File;

public class FileSet implements IFileSet {
    private File directory;
    private String[] expectedFiles;
    private String[] allowedFiles;
    
    public File getDirectory() {
        return directory;
    }
    
    public void setDirectory(File directory) {
        this.directory = directory;
    }
    
    public String[] getExpectedFiles() {
        return expectedFiles;
    }
    
    public void setExpectedFiles(String[] expectedFiles) {
        this.expectedFiles = expectedFiles;
    }
    
    public String[] getAllowedFiles() {
        return allowedFiles;
    }
    
    public void setAllowedFiles(String[] allowedFiles) {
        this.allowedFiles = allowedFiles;
    }
}
