package com.logger.utils.files;

import java.io.IOException;
import java.util.Formatter;

/**
 *
 * @author hikingcarrot7
 */
public class FileWriter extends FileHandler {

    public FileWriter(String path) {
	super(path);
    }

    public void appendLineToFile(String msg) {
	writeTextToFile(msg, true);
    }

    public void writeTextToFile(String msg, boolean append) {
	try (Formatter out = new Formatter(new java.io.FileWriter(file, append))) {
	    out.format("%s", msg);
	} catch (IOException ex) {
	    System.out.println(ex.getMessage());
	}
    }

}
