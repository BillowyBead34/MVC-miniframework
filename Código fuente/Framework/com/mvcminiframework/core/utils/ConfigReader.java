package com.mvcminiframework.core.utils;

import com.mvcminiframework.core.exceptions.ConfigFileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author hikingcarrot7
 */
public class ConfigReader {

    public static final String DEFAULT_CONFIG_PATH = "./config/mvcconfig.json";

    private final File file;

    public ConfigReader() {
	this(DEFAULT_CONFIG_PATH);
    }

    public ConfigReader(String path) {
	file = new File(path);
    }

    public String readConfigJson() {
	String json = "";
	try (Scanner scanner = new Scanner(file)) {
	    while (scanner.hasNext())
		json += scanner.nextLine();

	    return json;
	} catch (FileNotFoundException ex) {
	    throw new ConfigFileNotFoundException();
	}
    }

    public File getFile() {
	return file;
    }

}
