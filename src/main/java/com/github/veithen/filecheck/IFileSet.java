package com.github.veithen.filecheck;

import java.io.File;

public interface IFileSet {
    File getDirectory();
    String[] getExpectedFiles();
    String[] getAllowedFiles();
}
