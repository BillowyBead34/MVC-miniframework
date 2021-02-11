package com.logger.utils.files;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author hikingcarrot7
 */
public class FileHandler {

    protected File file;

    public FileHandler(String path) {
	file = new File(path);
    }

    public boolean existsFile() {
	return file.exists();
    }

    public void createFile() throws IOException {
	file.createNewFile();
    }

    public void makeDirectory() {
	file.mkdirs();
    }

    public long length() {
	return file.length();
    }

    public File getFile() {
	return file;
    }

    public void setFile(File file) {
	this.file = file;
    }

}
