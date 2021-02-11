package com.logger;

/**
 *
 * @author hikingcarrot7
 */
public class LoggerConfigInfo {

    private final long maxFileSize;
    private final boolean printDate;
    private final String logsDirectory;

    public LoggerConfigInfo(long fileMaxSize, boolean printDate, String logsDirectory) {
	this.maxFileSize = fileMaxSize;
	this.printDate = printDate;
	this.logsDirectory = logsDirectory;
    }

    public long getFileMaxSize() {
	return maxFileSize;
    }

    public boolean printDate() {
	return printDate;
    }

    public String getLoggerPath() {
	return logsDirectory;
    }

    @Override public String toString() {
	return "LoggerInfo{" + "fileMaxSize=" + maxFileSize + ", printDate=" + printDate + ", logsDirectory=" + logsDirectory + '}';
    }

}
