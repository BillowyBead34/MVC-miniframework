package com.logger.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Objects;
import com.logger.LoggerConfigInfo;
import com.logger.exceptions.ConfigFileNotFoundException;
import com.logger.exceptions.InvalidConfigFileException;
import com.logger.utils.config.ConfigFileReader;

/**
 *
 * @author hikingcarrot7
 */
public class LoggerConfigParser {

    public static final String MAX_FILE_SIZE_ATTRIB = "maxFileSize";
    public static final String PRINT_DATE_ATTRIB = "printDate";
    public static final String LOGS_DIRECTORY_ATTRIB = "logsDirectory";

    private final ConfigFileReader reader;
    private final Gson gson;

    public LoggerConfigParser() {
        this(ConfigFileReader.DEFAULT_CONFIG_PATH);
    }

    public LoggerConfigParser(String path) {
        reader = new ConfigFileReader(path);
        gson = new Gson();
    }

    public LoggerConfigInfo parseJsonConfig() {
        return parseJsonConfig(getJsonObject());
    }

    private LoggerConfigInfo parseJsonConfig(JsonObject jsonObject) {
        JsonElement maxFileSize = jsonObject.get("maxFileSize");
        JsonElement printDate = jsonObject.get("printDate");
        JsonElement loggerPath = jsonObject.get("logsDirectory");

        Objects.requireNonNull(maxFileSize, getErrorDisplayMsg(MAX_FILE_SIZE_ATTRIB));
        Objects.requireNonNull(printDate, getErrorDisplayMsg(PRINT_DATE_ATTRIB));
        Objects.requireNonNull(loggerPath, getErrorDisplayMsg(LOGS_DIRECTORY_ATTRIB));

        return new LoggerConfigInfo(
                maxFileSize.getAsLong(),
                printDate.getAsBoolean(),
                loggerPath.getAsString());
    }

    private String getErrorDisplayMsg(String args) {
        return String.format("No encontramos el atributo %s en el archivo de configuración", args);
    }

    private JsonObject getJsonObject() throws ConfigFileNotFoundException {
        try {
            return gson.fromJson(reader.readFileContent(), JsonObject.class);
        } catch (JsonSyntaxException e) {
            throw new InvalidConfigFileException(e.getMessage());
        }
    }

}
