package com.github.veithen.filecheck;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="check-multiple")
public class CheckMultipleMojo extends AbstractCheckMojo {
    @Parameter(required=true)
    private FileSet[] fileSets;

    @Override
    protected IFileSet[] getFileSets() {
        return fileSets;
    }
}
