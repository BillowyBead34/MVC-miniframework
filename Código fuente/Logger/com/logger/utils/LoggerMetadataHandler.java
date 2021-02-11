package com.logger.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import com.logger.utils.files.FileReader;
import com.logger.utils.files.FileWriter;

/**
 *
 * @author hikingcarrot7
 */
public class LoggerMetadataHandler {

    public static final String DEFAULT_LOGGER_METADATA_FOLDER = "./logger/";
    public static final String DEFAULT_LOGGER_METADATA_PATH = DEFAULT_LOGGER_METADATA_FOLDER + "loggermetadata.json";

    private final FileReader fileReader;
    private final FileWriter fileWriter;
    private final Gson gson;

    public LoggerMetadataHandler() {
        this(DEFAULT_LOGGER_METADATA_PATH);
    }

    public LoggerMetadataHandler(String path) {
        fileWriter = new FileWriter(path);
        fileReader = new FileReader(path);
        gson = new Gson();
    }

    public int getCurrentLogFileIdx() {
        JsonObject jsonObject = gson.fromJson(readLoggerMetadataFile(), JsonObject.class);
        JsonElement currentLogFileIdx = jsonObject.get("logFileIdx");

        if (currentLogFileIdx == null) {
            createLoggerMetadataFile();
            return getCurrentLogFileIdx();
        }

        return currentLogFileIdx.getAsInt();
    }

    public void incrementLogFileIdx() {
        int currentLogfileIdx = getCurrentLogFileIdx();
        currentLogfileIdx++;
        writeLogFileIdx(currentLogfileIdx);
    }

    public String readLoggerMetadataFile() {
        if (!fileReader.existsFile())
            createLoggerMetadataFile();

        return fileReader.readFileContent();
    }

    private void createLoggerMetadataFile() {
        try {
            createLoggerDataFolder();
            fileWriter.createFile();
            writeLogFileIdx(1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeLogFileIdx(int currentLogFileIdx) {
        fileWriter.writeTextToFile(String.format("{\"logFileIdx\":%d}", currentLogFileIdx), false);
    }

    private void createLoggerDataFolder() {
        File file = new File(DEFAULT_LOGGER_METADATA_FOLDER);
        file.mkdir();
    }

}
