package com.logger.utils;

import com.logger.LoggerConfigInfo;
import com.logger.utils.files.FileWriter;
import java.io.File;
import java.io.IOException;
import com.logger.LoggerConfigInfo;

/**
 *
 * @author hikingcarrot7
 */
public class LoggerRecordHandler {

    public static final String LOG_FILE_NAME_TEMPLATE = "%slog_%d.txt";

    private final LoggerConfigInfo configInfo;
    private final FileWriter fileWriter;
    private final LoggerMetadataHandler loggerMetadataHandler;

    public LoggerRecordHandler(LoggerConfigInfo loggerInfo) {
	this(new LoggerMetadataHandler(), loggerInfo);
    }

    public LoggerRecordHandler(LoggerMetadataHandler loggerMetadataHandler, LoggerConfigInfo configInfo) {
	this.configInfo = configInfo;
	this.loggerMetadataHandler = loggerMetadataHandler;
	this.fileWriter = new FileWriter(formatLogFilesPath(configInfo, loggerMetadataHandler));
	createLogsDirectory(configInfo.getLoggerPath());
    }

    public void recordLog(String msg) {
	if (!fileWriter.existsFile())
	    createLogFile();

	checkCurrentLogFileLength();

	fileWriter.appendLineToFile(msg + "\r\n");
    }

    private void checkCurrentLogFileLength() {
	if (fileWriter.length() > configInfo.getFileMaxSize()) {
	    loggerMetadataHandler.incrementLogFileIdx();
	    fileWriter.setFile(new File(formatLogFilesPath(configInfo, loggerMetadataHandler)));
	}
    }

    private void createLogsDirectory(String logsDirectory) {
	File file = new File(logsDirectory);

	if (!file.exists())
	    file.mkdirs();
    }

    private void createLogFile() {
	try {
	    fileWriter.createFile();
	} catch (IOException ex) {
	    System.out.println(ex.getMessage());
	}
    }

    private String formatLogFilesPath(LoggerConfigInfo loggerInfo, LoggerMetadataHandler metadataHandler) {
	return formatLogFilesPath(loggerInfo, metadataHandler.getCurrentLogFileIdx());
    }

    private String formatLogFilesPath(LoggerConfigInfo loggerInfo, int currentLogFileIdx) {
	return formatLogFilesPath(loggerInfo.getLoggerPath(), currentLogFileIdx);
    }

    private String formatLogFilesPath(String logsDirectory, int currentLogFileIdx) {
	return String.format(LOG_FILE_NAME_TEMPLATE, logsDirectory, currentLogFileIdx);
    }

}
