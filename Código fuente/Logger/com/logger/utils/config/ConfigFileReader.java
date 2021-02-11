package com.logger.utils.config;

import com.logger.utils.files.FileReader;

/**
 *
 * @author hikingcarrot7
 */
public class ConfigFileReader extends FileReader {

    public static final String DEFAULT_CONFIG_PATH = "./config/loggerconfig.json";

    public ConfigFileReader() {
	this(DEFAULT_CONFIG_PATH);
    }

    public ConfigFileReader(String path) {
	super(DEFAULT_CONFIG_PATH);
    }

}
